package com.project.daerkoob.service;

import com.project.daerkoob.domain.Review;
import com.project.daerkoob.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReview(Long bookId){
        List<Review> reviews = reviewRepository.findByBookId(bookId);
        return reviews;
    }

    public Review createDto(Long userId , Long bookId , String reviewContent){
        Review review = new Review();
        review.setUserId(userId);
        review.setBookId(bookId);
        review.setStar(0D);
        review.setThumb(0L);
        review.setStarCount(0L);
        review.setReview(reviewContent);
        return review;
    }

    public void save(Review review){
        reviewRepository.save(review);
    }
}
