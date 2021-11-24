package com.project.daerkoob.service;

import com.project.daerkoob.domain.*;
import com.project.daerkoob.model.TransferComment;
import com.project.daerkoob.repository.BookRepository;
import com.project.daerkoob.repository.CommentRepository;
import com.project.daerkoob.repository.ReviewRepository;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;
    private CommentRepository commentRepository;

    public ReviewService(ReviewRepository reviewRepository , UserRepository userRepository , BookRepository bookRepository, CommentRepository commentRepository){
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    public List<Review> getBookReview(Long bookId){
        List<Review> reviews = reviewRepository.findByBook(bookRepository.findById(bookId).get());
        return reviews;
    }

    public List<Review> getUserReview(Long userId){
        List<Review> reviews = reviewRepository.findByUser(userRepository.findById(userId).get());
        return reviews;
    }
    public List<TransferComment> getCommentOfReview(Long reviewId){ //review에 대한 댓글을 다 불러오는 메소드
        List<TransferComment> resultList = new ArrayList<>();
        List<Comment> commentList = commentRepository.findByReview(reviewRepository.findById(reviewId).get());
        for(Comment comment : commentList){
            resultList.add(createTransferComment(comment));
        }
        return resultList;
    }

    public TransferComment createTransferComment(Comment comment){
        TransferComment transferComment = new TransferComment();
        transferComment.setId(comment.getId());
        transferComment.setComments(comment.getComments());
        transferComment.setWriter(comment.getWriter());
        transferComment.setReview(comment.getReview());
        transferComment.setThumbCount(comment.getThumbCount());
        transferComment.setContent(comment.getContent());
        return transferComment;
    }
    public Review createDto(Long userId , Long bookId , Long score , String reviewContent){
        Review review = new Review();
        LocalDate now = LocalDate.now();
        review.setUser(userRepository.findById(userId).get());
        review.setBook(bookRepository.findById(bookId).get());
        review.setScore(score);
        review.setThumbCount(0L);
        review.setContent(reviewContent);
        review.setRegisterDate(now);
        return review;
    }

    public Message save(Review review){ //저장하면서 book의 정보를 건드려야 함
        reviewRepository.save(review);
        Optional<User> findByUser = userRepository.findById(review.getUser().getId());
        Optional<Book> findByBook = bookRepository.findById(review.getBook().getId());
        User resultUser = findByUser.get();
        Book resultBook = findByBook.get();
        resultUser.setReviewCount(resultUser.getReviewCount() + 1);
        resultBook.setStar(scoreCalculate(resultBook.getStar(), resultBook.getStarCount() , review.getScore() , resultBook.getStarCount() + 1));
        resultBook.setStarCount(resultBook.getStarCount() + 1);
        resultBook.setReviewCount(resultBook.getReviewCount() + 1);
        userRepository.save(resultUser);
        bookRepository.save(resultBook);
        return new Message(true , "리뷰 저장에 성공했습니다.");
    }

    public Double scoreCalculate(Double previousScore , Long previousCount , Long score , Long presentCount){ //별점 주기
        Double resultScore = (double)Math.round(((previousScore * previousCount) + score) / presentCount * 100) / 100;
        return resultScore;
    }
}