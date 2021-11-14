package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Star;
import com.project.daerkoob.domain.Thumb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThumbRepository extends JpaRepository<Thumb, Long> {
    Optional<Thumb> findByUserIdAndBookIdAndReviewIdAndGivenUserId(Long userId , Long bookId , Long reviewId , Long givenUserId);
    Optional<Thumb> findByUserIdAndBookIdAndTranscriptionIdAndGivenUserId(Long userId , Long bookId , Long transcriptionId , Long givenUserId);
}
