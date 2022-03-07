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

    @GetMapping("inquiry/{pageNumber}") // 얘는 뭐 유저 이런 거 상관 없다 , 좋아요 이런 것도 안만들 거다.
    public MessageWithList getNotice(@PathVariable Long pageNumber){
        return noticeService.getNotice(pageNumber);
    }

//    @PostMapping("register")
//    public Message setNotice(Long userIndex , String title , String content){
//        return noticeService.setNotice(userIndex , title , content);
//    }

}