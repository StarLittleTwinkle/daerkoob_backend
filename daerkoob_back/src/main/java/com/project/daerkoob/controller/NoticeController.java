package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("notice")
public class NoticeController {
    private final NoticeService noticeService;

    // notice 불러오기
    // 저장은 database에다가 직접하는 형식? 으로 할 것이라서 register는 만들지 않아도 될 듯
    // 게시판 형식으로 database를 list 들을 주고 프론트에서 선택하는 형식?
    // 그냥 notice 들을 다 넘기기만 하면 될까?
    @GetMapping("inquiry/{pageNumber}") // 얘는 뭐 유저 이런 거 상관 없다 , 좋아요 이런 것도 안만들 거다.
    public MessageWithList getNotice(@PathVariable Long pageNumber){
        return noticeService.getNotice(pageNumber);
    }
    @PostMapping("register")
    public Message setNotice(Long userIndex , String title , String content){
        return noticeService.setNotice(userIndex , title , content);
    }

}