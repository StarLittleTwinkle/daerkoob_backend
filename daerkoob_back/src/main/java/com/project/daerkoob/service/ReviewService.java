package com.project.daerkoob.service;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.repository.ReviewRepository;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository , UserRepository userRepository){
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public List<Review> getReview(Long bookId){
        List<Review> reviews = reviewRepository.findByBookId(bookId);
        return reviews;
    }

    public Review createDto(Long userId , Long bookId , String bookTitle , String userNickName, String reviewContent){
        Review review = new Review();
        LocalDate now = LocalDate.now();
        review.setUserId(userId);
        review.setBookId(bookId);
        review.setBookTitle(bookTitle);
        review.setUserNickName(userNickName);
        review.setStar(0D);
        review.setThumb(0L);
        review.setStarCount(0L);
        review.setReview(reviewContent);
        review.setRegisterDate(now);
        return review;
    }

    public Message save(Review review){
        reviewRepository.save(review);
        Optional<User> findById = userRepository.findById(review.getId());
        User resultUser = findById.get();
        resultUser.setReviewCount(resultUser.getReviewCount() + 1);
        userRepository.save(resultUser);
        return new Message(true , "리뷰 저장에 성공했습니다.");
    }
}
