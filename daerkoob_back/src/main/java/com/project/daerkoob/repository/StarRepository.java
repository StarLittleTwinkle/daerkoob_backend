package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StarRepository extends JpaRepository<Star,Long> {
    boolean existsByUserIdAndBookIdAndReviewIdAndGivenUserId(Long userId , Long bookId , Long reviewId , Long givenUserId);
    boolean existsByUserIdAndBookIdAndTranscriptionIdAndGivenUserId(Long userId , Long bookId , Long transcriptionId , Long givenUserId);
    Optional<Star> findByUserIdAndBookIdAndReviewIdAndGivenUserId(Long userId , Long bookId , Long reviewId , Long givenUserId);
    Optional<Star> findByUserIdAndBookIdAndTranscriptionIdAndGivenUserId(Long userId , Long bookId , Long transcriptionId , Long givenUserId);
}
