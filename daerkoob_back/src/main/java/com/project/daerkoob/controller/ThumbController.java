package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.service.ThumbService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("thumb")
public class ThumbController {

    ThumbService thumbService;
    public ThumbController(ThumbService thumbService){
        this.thumbService = thumbService;
    }

    @GetMapping("review/{userIndex}/{reviewId}") //좋아요 리뷰에다가 , guide line ,userIndex == 리뷰에 좋아요 누르는 사람
    public Message getSetReview(@PathVariable Long userIndex, @PathVariable Long reviewId){
        return thumbService.judgeReview(userIndex, reviewId);
    }
    @PostMapping("review")
    public Message setReview(Long userIndex, Long reviewId){
        return thumbService.judgeReview(userIndex, reviewId);
    }

    @GetMapping("transcription/{userIndex}/{transcriptionId}") //좋아요 필사에다가 , guide line
    public Message getSetTranscription(@PathVariable Long userIndex,@PathVariable Long transcriptionId){
        return thumbService.judgeTranscription(userIndex, transcriptionId);
    }

    @PostMapping("transcription")
    public Message setTranscription(Long userIndex, Long transcriptionId){
        return thumbService.judgeTranscription(userIndex, transcriptionId);
    }

    @GetMapping("comment/{userIndex}/{commentId}") //좋아요 필사에다가 , guide line
    public Message getSetComment(@PathVariable Long userIndex,@PathVariable Long commentId){
        return thumbService.judgeComment(userIndex, commentId);
    }


    @PostMapping("comment") //좋아요 필사에다가 , guide line
    public Message SetComment(Long userIndex, Long commentId){
        return thumbService.judgeComment(userIndex, commentId);
    }
}
