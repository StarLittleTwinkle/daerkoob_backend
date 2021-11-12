package com.project.daerkoob.service;

import com.project.daerkoob.repository.TranscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class TranscriptionService {
    private TranscriptionRepository transcriptionRepository;

    public TranscriptionService(TranscriptionRepository transcriptionRepository){
        this.transcriptionRepository = transcriptionRepository;
    }
}
