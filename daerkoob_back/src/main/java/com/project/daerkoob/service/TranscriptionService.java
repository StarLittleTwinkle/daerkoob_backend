package com.project.daerkoob.service;

import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.repository.TranscriptionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TranscriptionService {
    private TranscriptionRepository transcriptionRepository;

    public TranscriptionService(TranscriptionRepository transcriptionRepository){
        this.transcriptionRepository = transcriptionRepository;
    }

    public List<Transcription> getTranscription(Long bookId){
        List<Transcription> transcriptions = transcriptionRepository.findByBookId(bookId);
        return transcriptions;
    }

    public Transcription createDto(Long userId , Long bookId , String transcriptionContent){
        Transcription transcription = new Transcription();
        transcription.setUserId(userId);
        transcription.setBookId(bookId);
        transcription.setStar(0D);
        transcription.setThumb(0L);
        transcription.setStarCount(0L);
        transcription.setTranscription(transcriptionContent);
        return transcription;
    }

    public void save(Transcription transcription){
        transcriptionRepository.save(transcription);
    }
}
