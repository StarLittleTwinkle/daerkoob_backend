package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Transcription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptionRepository extends JpaRepository<Transcription , Long> {
}
