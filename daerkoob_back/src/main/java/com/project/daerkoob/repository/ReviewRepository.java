package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review , Long> {
    List<Review> findByBookId(Long bookId);
}
