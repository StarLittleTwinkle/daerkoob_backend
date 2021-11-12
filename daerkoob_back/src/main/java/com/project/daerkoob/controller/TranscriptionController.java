package com.project.daerkoob.controller;

import com.project.daerkoob.service.TranscriptionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("transcription")
public class TranscriptionController {

    private TranscriptionService transcriptionService; 

    public TranscriptionController(TranscriptionService transcriptionService){
        this.transcriptionService = transcriptionService;
    }
}
