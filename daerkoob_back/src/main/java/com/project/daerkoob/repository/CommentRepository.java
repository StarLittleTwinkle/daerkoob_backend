package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Comment;
import com.project.daerkoob.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment , Long> {
    List<Comment> findByReviewOrderByRegisterDateDesc(Review review);
    Long countByComment(Comment comment);
    Page<Comment> findByReview(Review review , Pageable pageable);
}
