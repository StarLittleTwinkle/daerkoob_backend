package com.project.daerkoob.controller;


import com.project.daerkoob.domain.Notice;
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
    public MessageWithList findAll(@PathVariable Long pageNumber){
        return noticeService.getNotice(pageNumber);
    }

    // 현재 만들어야 하는 기능...
    // register , update , delete 기능들을 만들고
    // 특정 게시물을 볼 수 있도록 , GetMapping 으로 만든다.

    @GetMapping("{noticeid}")
    public Notice findById(@PathVariable(name = "noticeid") Long id){
        return noticeService.findById(id);
    }

    // 게시글을 작성과 동시에 , List를 다시 넘겨받음 (0 페이지로)
    @PostMapping("register")
    public MessageWithList save(String title , String content){
         return noticeService.setNotice(title , content);
    }

    @PostMapping("update")
    public MessageWithList save(Long id , String title , String content){
        return noticeService.update(id , title , content);
    }

    @DeleteMapping("delete")
    public MessageWithList delete(Long id){
        return noticeService.delete(id);
    }

}