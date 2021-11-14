package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.Star;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.repository.StarRepository;
import com.project.daerkoob.service.StarService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("review") // 딱 별점 버튼을 눌렀을 때 나뉘게 된다. 이미 별점을 줬느냐 안줬느냐... 그리고 이 해당 필사 , 리뷰에 대한 평가가 이미 존재하느냐
    public Message setReview(Long userIndex , Review review , Long score){
        return starService.judgeReview(userIndex, review , score);
    }
    @PostMapping("transcription")
    public Message setTranscription(Long userIndex , Transcription transcription , Long score){
        return starService.judgeTranscription(userIndex, transcription , score);
    }
}
