package com.project.daerkoob.service;

import com.project.daerkoob.domain.*;
import com.project.daerkoob.model.*;
import com.project.daerkoob.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final ThumbRepository thumbRepository;

    public Long countAll() {
        return reviewRepository.count();
    }

    public Long getReviewCount(Book book){
        return reviewRepository.countByBook(book);
    }

    public List<TransferReview> getUserReview(Long userId){
        List<Review> reviews = reviewRepository.findByUser(userRepository.findById(userId).get());
        List<TransferReview> transferReviews = new ArrayList<>();
        for(Review review : reviews){
            transferReviews.add(createTransferReview(userId , review));
        }
        return transferReviews;
    }

    public TransferReview findById(Long userId , Long reviewId){
        return createTransferReview(userId , reviewRepository.findById(reviewId).get());
    }

    public MessageWithList reviewDelete(Long reviewId, Long userId) { //이제 해야할게 userId로 해당 user가 쓴게 맞는지 확인해야함
        //추후에는 여기서 판단하는 것이 아닌 이전에 review 조회할 때에도 자신이 쓴 review인지 조회할 수 있도록 , thumb처럼 수정해야할 듯
        Review review = reviewRepository.findById(reviewId).get();
        if (review.getUser().getId() != userId) { //같지 않은 경우 삭제 불가
            CountAndList transferReviews = getBookReview(userId , review.getBook().getId() , 1L);
            return new MessageWithList(new Long(transferReviews.getTotalCount()), new Message(false , "삭제에 실패했습니다."), new ArrayList<>(transferReviews.getList()));
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
            CountAndList transferReviews = getBookReview(userId , review.getBook().getId() , 1L);
            return new MessageWithList(new Long(transferReviews.getTotalCount()), new Message(true, "삭제에 성공했습니다."), new ArrayList<>(transferReviews.getList()));
        }
    }

    public boolean getReview(Long bookId) {
        return reviewRepository.existsByBookId(bookId);
    }

    public MessageWithList getMessageWithListOfBookReview(Long userId , Long bookId , Long pageNumber){ // getBookReview가 List<TransferReview> 로 넘겨주면 MessageWithList로 변환해서 넘겨준다.
        CountAndList transferReviews = getBookReview(userId , bookId , pageNumber);
        return new MessageWithList(new Long(transferReviews.getTotalCount()) , new Message(true , "리뷰를 성공적으로 가져왔습니다.") , new ArrayList<>(transferReviews.getList()));
    }

    public CountAndList getBookReview(Long userId, Long bookId , Long pageNumber) {
        Page<Review> reviews = reviewRepository.findByBookIdOrderByRegisterDateDesc(bookId , PageRequest.of(pageNumber.intValue() , 10));

        List<TransferReview> resultList = reviews.getContent().stream()
                .map(review -> createTransferReview(userId , review))
                .collect(Collectors.toList());

        return new CountAndList(reviews.getTotalElements() , new ArrayList<>(resultList));
    }

    // 가장 핵심적인 내용은 thumbJudge 를 같이 가져오는 것
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

    public CountAndList getCommentOfReview(Long reviewId , Long userId , Long pageNumber) { //review에 대한 댓글을 다 불러오는 메소드
        /*
        일단 CountAndList로 반환을 할 것임 TotalCount와 list로
        일단 그럴려면 pagination을 넘겨야함 , reviewId를 넣어서 넘기자

        일단 그냥 댓글의 개수들을 알 수 있어야한다.
        그리고 대댓글의 개수들도 알아내야 한다.
        총 댓글의 개수 = 댓글의 개수 + 대댓글의 개수
        즉 , 댓글의 개수 , 대댓글의 개수를 따로 알아낼 수 있다면?
        두개의 개수를 알아낼 수 있다.
         */
        List<TransferComment> resultList = new ArrayList<>();
        Review review = reviewRepository.findById(reviewId).orElseThrow(()
                -> {throw new EntityNotFoundException();});

        // 대댓글의 개수
        Long nestedCommentCount = 0L;
        // 댓글의 개수
        Long commentCount = 0L;

        Long mul = 10L;
        for (Comment comment : commentRepository.findByReview(review)) { // 그냥 pagination 을 수동적으로 진행하였음

            if(pageNumber * mul <= commentCount && commentCount < pageNumber * mul + mul){
                resultList.add(createTransferComment(comment , userId));
            }

            nestedCommentCount += getNestedCommentCount(comment);
            commentCount++; // 댓글이 점점 쌓여감
        }

        // nestedCommentCount == 대 댓글 개수 , commentCount == 그냥 댓글 개수
        return new CountAndList(nestedCommentCount + commentCount , commentCount ,  new ArrayList<>(resultList));
    }

    public List<Review> getRecentReview(){
        return reviewRepository.findTop8ByOrderByRegisterDateDesc();
    }

    public Long getNestedCommentCount(Comment comment){
         return new Long(comment.getComments().size());
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