package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranscriptionRepository extends JpaRepository<Transcription , Long> {
    long count();
    List<Transcription> findByBookId(Long bookId);
    List<Transcription> findByUser(User user);
    List<Transcription> findTop8ByOrderByRegisterDateDesc();
}
