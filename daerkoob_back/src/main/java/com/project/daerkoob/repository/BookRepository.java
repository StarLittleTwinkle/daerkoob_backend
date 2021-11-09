package com.project.daerkoob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.daerkoob.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    Optional<Book> findByIsbn(String isbn);
}
