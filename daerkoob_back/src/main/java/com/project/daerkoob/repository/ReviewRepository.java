package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.daerkoob.domain.Book;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBook(Book book);
    List<Review> findByUser(User user);
    long count();
}
