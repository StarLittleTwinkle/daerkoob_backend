package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.service.ThumbService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("thumb")
public class ThumbController {

    ThumbService thumbService;
    public ThumbController(ThumbService thumbService){
        this.thumbService = thumbService;
    }

    @PostMapping("review")
    public Message setReview(Long userIndex , Review review){
        return thumbService.judgeReview(userIndex , review);
    }
    @PostMapping("transcription")
    public Message setTranscription(Long userIndex , Transcription transcription){
        return thumbService.judgeTranscription(userIndex , transcription);
    }
}
