package com.project.daerkoob.service;

import com.project.daerkoob.domain.*;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.model.TransferComment;
import com.project.daerkoob.model.TransferReview;
import com.project.daerkoob.repository.*;
import org.springframework.stereotype.Service;

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
            List<TransferReview> transferReviews = getBookReview(userId , review.getBook().getId());
            return new MessageWithList(new Long(transferReviews.size()), new Message(false , "삭제에 실패했습니다."), new ArrayList<>(transferReviews));
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
            List<TransferReview> transferReviews = getBookReview(userId , review.getBook().getId());
            return new MessageWithList(new Long(transferReviews.size()), new Message(true, "삭제에 성공했습니다."), new ArrayList<>(transferReviews));
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
        /*
        -- 추가구현
        1. 여기서 review에 달려있는 댓글의 개수 , 대댓글까지 포함해서 같이 반환하기
        2. 안에 포함되어 있는 댓글들도 대댓글을 가지고 있으니까 카테고리를 리뷰에 달린 댓글 총 개수(대댓글까지) , 댓글들은 자신들의 대댓글 개수를 가지고 있는 것
        3. 문제 발생함 , 이전에 deleteComment로 했었던 것이 문제가 일어남 나머지도 아마 다 똑같이 할텐데 , 그럴려면 나머지도 다 수정해야함..
        4. 이러면 그냥 여기서는 이전처럼 List<TransferComment> 를 보내고 그거를 받아서 따로 공정해서 반환하는 과정을 거쳐야 할 것 같음
         */
        List<TransferComment> resultList = new ArrayList<>();
        List<Comment> commentList = commentRepository.findByReview(reviewRepository.findById(reviewId).get());
        for (Comment comment : commentList) {
            resultList.add(createTransferComment(comment , userId));
        }
//        Long totalSize = 0L;
//        for(TransferComment transferComment : resultList){
//            totalSize++;
//            totalSize += transferComment.getNestedCount();
//        }
//        CountWithList result = new CountWithList(totalSize , new ArrayList<>(resultList));
        return resultList;
    }

    public List<Review> getRecentReview(){
        return reviewRepository.findTop8ByOrderByRegisterDateDesc();
    }

    public TransferComment createTransferComment(Comment comment , Long userId) { //대댓글에 쓰이는 transferComment
        // 대댓글도 좋아요를 분간 할 수 있도록 다시 구현해놓았음
        TransferComment transferComment = new TransferComment();
        transferComment.setId(comment.getId());
        List<Comment> commentList = comment.getComments();
        List<TransferComment> nestedCommentList = new ArrayList<>();
        for(Comment nestedComment : commentList){
            nestedCommentList.add(createTransferCommentOfNested(nestedComment , userId));
        }
        transferComment.setNestedCount(new Long(nestedCommentList.size())); // 자신에게 달린 댓글의 개수를 세는 것
        transferComment.setComments(nestedCommentList);
        transferComment.setWriter(comment.getWriter());
        transferComment.setThumbCount(comment.getThumbCount());
        transferComment.setThumbJudge(thumbRepository.existsByCommentIdAndGivenUserId(comment.getId() , userId)); // 이 user 가 좋아요를 눌렀는지 아닌지 판단
        transferComment.setContent(comment.getContent());
        transferComment.setRegisterDate(comment.getRegisterDate());
        return transferComment;
    }

    public TransferComment createTransferCommentOfNested(Comment comment , Long userId){
        TransferComment transferComment = new TransferComment();
        transferComment.setId(comment.getId());
        transferComment.setNestedCount(null); // 얘는 대댓글이니까 대댓글은 자식의 개수가 없으니 null로 설정
        transferComment.setComments(null);
        transferComment.setWriter(comment.getWriter());
        transferComment.setThumbCount(comment.getThumbCount());
        transferComment.setThumbJudge(thumbRepository.existsByCommentIdAndGivenUserId(comment.getId() , userId)); // 이 user 가 좋아요를 눌렀는지 아닌지 판단
        transferComment.setContent(comment.getContent());
        transferComment.setRegisterDate(comment.getRegisterDate());
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