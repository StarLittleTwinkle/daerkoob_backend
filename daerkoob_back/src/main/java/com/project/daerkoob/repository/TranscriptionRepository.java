package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.daerkoob.domain.Book;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TranscriptionRepository extends JpaRepository<Transcription , Long> {
    long count();
    List<Transcription> findByBook(Book book);
    List<Transcription> findByUser(User user);
    List<Transcription> findTop8ByOrderByRegisterDateDesc();
    List<Transcription> findByUserAndRegisterDateBetweenOrderByRegisterDateAsc(User user , LocalDateTime registerDate1 , LocalDateTime registerDate2);
}
