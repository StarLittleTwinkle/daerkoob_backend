package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Review;
import com.project.daerkoob.service.StarService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("star")
public class StarController {

    StarService starService;

    public StarController(StarService starService){
        this.starService = starService;
    }

    @PostMapping("judge") // 딱 별점 버튼을 눌렀을 때 나뉘게 된다. 이미 별점을 줬느냐 안줬느냐... 그리고 이 해당 필사 , 리뷰에 대한 평가가 이미 존재하느냐
    public void judge(Long userIndex , Review review , Long score){
        //해당 사용자가 누구냐를 판단 그리고서 별점을 이미 주었는지 안 주었는지를 판단한다.
    }
}
