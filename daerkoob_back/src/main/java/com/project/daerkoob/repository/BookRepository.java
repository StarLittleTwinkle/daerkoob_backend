package com.project.daerkoob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.daerkoob.domain.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Boolean existsByIsbn(String isbn); //없는 method일 경우에는 그냥 ByName해주면 됨
    Optional<Book> findByIsbn(String isbn);
    List<Book> findTop10ByOrderByTranscriptionCountDesc();
    List<Book> findTop18ByOrderByTranscriptionCountDesc();
    long count();
}
