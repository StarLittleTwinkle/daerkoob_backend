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
        //비교적 단순할 듯
        //딱 리뷰에 좋아요 누르면 , 이것도 똑같이 이미 누른 상태인지 , 이 유저가 누른 상태인지 아닌지를 판단한다 , 이제 이 유저가 늘렀으면 ? 취소 , 아니면 좋아요
        return thumbService.judgeReview(userIndex , review);
        }
    @PostMapping("transcription")
    public Message setTranscription(Long userIndex , Transcription transcription){
        return thumbService.judgeTranscription(userIndex , transcription);
    }
}
