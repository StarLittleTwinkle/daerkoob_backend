package com.project.daerkoob.controller;

import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.service.NoticeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("notice")
public class NoticeController {
    private NoticeService noticeService;

    public NoticeController(NoticeService noticeService){
        this.noticeService = noticeService;
    }

    // notice 불러오기
    // 저장은 database에다가 직접하는 형식? 으로 할 것이라서 register는 만들지 않아도 될 듯
    // 게시판 형식으로 database를 list 들을 주고 프론트에서 선택하는 형식?
    // 그냥 notice 들을 다 넘기기만 하면 될까?
    public MessageWithList getNotice(){
        return noticeService.getNotice();
    }
}