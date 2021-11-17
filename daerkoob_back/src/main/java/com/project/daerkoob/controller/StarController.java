package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.Star;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.repository.StarRepository;
import com.project.daerkoob.service.StarService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("star")
public class StarController {

    StarService starService;
    StarRepository starRepository;

    public StarController(StarService starService , StarRepository starRepository){
        this.starService = starService;
        this.starRepository = starRepository;
    }

    @GetMapping("review/{userIndex}/{userId}/{bookId}/{reviewId}/{score}") //guide line , userIndex는 해당 별점을 준 user의 index임,
    public Message getSetReview(@PathVariable Long userIndex ,@PathVariable Long userId ,@PathVariable Long bookId ,@PathVariable Long reviewId ,@PathVariable Long score){
        return starService.judgeReview(userIndex, userId , bookId, reviewId , score);
    }

    @PostMapping("review") // 딱 별점 버튼을 눌렀을 때 나뉘게 된다. 이미 별점을 줬느냐 안줬느냐... 그리고 이 해당 필사 , 리뷰에 대한 평가가 이미 존재하느냐
    public Message setReview(Long userIndex , Long userId , Long bookId , Long reviewId , Long score){
        return starService.judgeReview(userIndex, userId , bookId, reviewId , score);
    }

    @GetMapping("transcription/{userIndex}/{userId}/{bookId}/{transcriptionId}/{score}") //guide line
    public Message getSetTranscription(@PathVariable Long userIndex ,@PathVariable Long userId ,@PathVariable Long bookId ,@PathVariable Long transcriptionId ,@PathVariable Long score){
        return starService.judgeTranscription(userIndex, userId, bookId , transcriptionId , score);
    }

    @PostMapping("transcription")
    public Message setTranscription(Long userIndex , Long userId , Long bookId , Long transcriptionId , Long score){
        return starService.judgeTranscription(userIndex, userId, bookId , transcriptionId , score);
    }
}
