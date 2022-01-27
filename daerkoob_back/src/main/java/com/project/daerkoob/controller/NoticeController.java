package com.project.daerkoob.controller;

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
}