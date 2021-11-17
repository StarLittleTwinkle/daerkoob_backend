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

    @GetMapping("review/{userIndex}/{userId}/{bookId}/{reviewId}") //좋아요 리뷰에다가 , guide line
    public Message getSetReview(@PathVariable Long userIndex, @PathVariable Long userId , @PathVariable Long bookId , @PathVariable Long reviewId){
        return thumbService.judgeReview(userIndex, userId , bookId , reviewId);
    }
    @PostMapping("review")
    public Message setReview(Long userIndex , Long userId , Long bookId , Long reviewId){
        return thumbService.judgeReview(userIndex , userId , bookId , reviewId);
    }
    @GetMapping("transcription/{userIndex}/{userId}/{bookId}/{transcriptionId}") //좋아요 필사에다가 , guide line
    public Message getSetTranscription(@PathVariable Long userIndex ,@PathVariable Long userId,@PathVariable Long bookId ,@PathVariable Long transcriptionId){
        return thumbService.judgeTranscription(userIndex , userId, bookId, transcriptionId);
    }

    @PostMapping("transcription")
    public Message setTranscription(Long userIndex , Long userId, Long bookId , Long transcriptionId){
        return thumbService.judgeTranscription(userIndex , userId, bookId , transcriptionId);
    }
}
