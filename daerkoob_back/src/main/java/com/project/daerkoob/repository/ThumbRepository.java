package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Thumb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThumbRepository extends JpaRepository<Thumb , Long> {
    Optional<Thumb> findByReviewIdAndGivenUserId(Long reviewId , Long givenUserId);
    Optional<Thumb> findByTranscriptionIdAndGivenUserId(Long transcriptionId , Long givenUserId);
    Optional<Thumb> findByCommentIdAndGivenUserId(Long commentId , Long givenUserId);
}
