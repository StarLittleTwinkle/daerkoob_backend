package com.project.daerkoob.service;

import com.project.daerkoob.domain.*;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.model.TransferComment;
import com.project.daerkoob.model.TransferReview;
import com.project.daerkoob.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;
    private CommentRepository commentRepository;
    private ThumbRepository thumbRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository, CommentRepository commentRepository, ThumbRepository thumbRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.thumbRepository = thumbRepository;
    }

    public Long countAll() {
        return reviewRepository.count();
    }

    public List<TransferReview> getUserReview(Long userId){
        List<Review> reviews = reviewRepository.findByUser(userRepository.findById(userId).get());
        List<TransferReview> transferReviews = new ArrayList<>();
        for(Review review : reviews){
            transferReviews.add(createTransferReview(userId , review));
        }
        return transferReviews;
    }

    public MessageWithList reviewDelete(Long reviewId, Long userId) { //이제 해야할게 userId로 해당 user가 쓴게 맞는지 확인해야함
        //추후에는 여기서 판단하는 것이 아닌 이전에 review 조회할 때에도 자신이 쓴 review인지 조회할 수 있도록 , thumb처럼 수정해야할 듯
        Review review = reviewRepository.findById(reviewId).get();
        if (review.getUser().getId() != userId) { //같지 않은 경우 삭제 불가
            return new MessageWithList(false, "삭제에 실패했습니다.", new ArrayList<>(getBookReview(userId, review.getBook().getId())));
        } else {
            reviewRepository.deleteById(reviewId);
            User user = userRepository.findById(userId).get();
            user.setReviewCount(user.getReviewCount() - 1);
            Book book = bookRepository.findById(review.getBook().getId()).get();
            book.setStar(scoreCalculate(book.getStar(), book.getStarCount(), -1 * review.getScore(), book.getStarCount() - 1));
            book.setStarCount(book.getStarCount() - 1);
            book.setReviewCount(book.getReviewCount() - 1); //reveiw count까지 차감
            bookRepository.save(book); //별점 업데이트 후 저장 까지 완료
            userRepository.save(user);
            return new MessageWithList(true, "삭제에 성공했습니다.", new ArrayList<>(getBookReview(userId, review.getBook().getId())));
        }
    }

    public List<Review> getReview(Long bookId) {
        return new ArrayList<Review>();
    }

    public List<TransferReview> getBookReview(Long userId, Long bookId) {
        List<Review> reviews = reviewRepository.findByBook(bookRepository.findById(bookId).get());
        List<TransferReview> resultList = new ArrayList<>();
        for (Review review : reviews) {
            resultList.add(createTransferReview(userId, review));
        }
        return resultList;
    }

    public TransferReview createTransferReview(Long userId, Review review) {
        TransferReview transferReview = new TransferReview();
        transferReview.setBook(review.getBook());
        transferReview.setContent(review.getContent());
        transferReview.setId(review.getId());
        transferReview.setRegisterDate(review.getRegisterDate());
        transferReview.setScore(review.getScore());
        transferReview.setThumbJudge(thumbRepository.existsByReviewIdAndGivenUserId(review.getId(), userId));
        transferReview.setUser(review.getUser());
        transferReview.setThumbCount(review.getThumbCount());
        return transferReview;
    }

//    public List<Review> getUserReview(Long userId) {
//        List<Review> reviews = reviewRepository.findByUser(userRepository.findById(userId).get());
//        return reviews;
//    }

    public List<TransferComment> getCommentOfReview(Long reviewId , Long userId) { //review에 대한 댓글을 다 불러오는 메소드
        List<TransferComment> resultList = new ArrayList<>();
        List<Comment> commentList = commentRepository.findByReview(reviewRepository.findById(reviewId).get());
        for (Comment comment : commentList) {
            resultList.add(createTransferComment(comment , userId));
        }
        return resultList;
    }

    public List<Review> getRecentReview(){
        return reviewRepository.findTop8ByOrderByRegisterDateDesc();
    }

    public TransferComment createTransferComment(Comment comment , Long userId) { //대댓글에 쓰이는 transferComment
        TransferComment transferComment = new TransferComment();
        transferComment.setId(comment.getId());
        transferComment.setComments(comment.getComments());
        transferComment.setWriter(comment.getWriter());
        transferComment.setThumbCount(comment.getThumbCount());
        transferComment.setThumbJudge(thumbRepository.existsByCommentIdAndGivenUserId(comment.getId() , userId));
        transferComment.setContent(comment.getContent());
        return transferComment;
    }

    public Review createDto(Long userId, Long bookId, Double score, String reviewContent) {
        Review review = new Review();
        LocalDateTime now = LocalDateTime.now();
        review.setUser(userRepository.findById(userId).get());
        review.setBook(bookRepository.findById(bookId).get());
        review.setScore(score);
        review.setThumbCount(0L);
        review.setContent(reviewContent);
        review.setRegisterDate(now);
        return review;
    }

    public Message save(Review review) { //저장하면서 book의 정보를 건드려야 함
        reviewRepository.save(review);
        Optional<User> findByUser = userRepository.findById(review.getUser().getId());
        Optional<Book> findByBook = bookRepository.findById(review.getBook().getId());
        User resultUser = findByUser.get();
        Book resultBook = findByBook.get();
        resultUser.setReviewCount(resultUser.getReviewCount() + 1);
        resultBook.setStar(scoreCalculate(resultBook.getStar(), resultBook.getStarCount(), review.getScore(), resultBook.getStarCount() + 1));
        resultBook.setStarCount(resultBook.getStarCount() + 1);
        resultBook.setReviewCount(resultBook.getReviewCount() + 1);
        userRepository.save(resultUser);
        bookRepository.save(resultBook);
        return new Message(true, "리뷰 저장에 성공했습니다.");
    }

    public Double scoreCalculate(Double previousScore, Long previousCount, Double score, Long presentCount) { //별점 주기
        Double resultScore = (double) Math.round(((previousScore * previousCount) + score) / presentCount * 100) / 100;
        return resultScore;
    }
}