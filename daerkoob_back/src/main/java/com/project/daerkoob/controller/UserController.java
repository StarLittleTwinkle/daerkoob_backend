package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.model.*;
import com.project.daerkoob.service.ReviewService;
import com.project.daerkoob.service.TranscriptionService;
import com.project.daerkoob.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final TranscriptionService transcriptionService;
    private final ReviewService reviewService;

    @PostMapping("signup")
    public Message signUp(User user, String confirmPassword){
        return userService.signUp(user, confirmPassword);
    }

    @PostMapping("login")
    public TransferUser login(User user) { //친구 목록까지 넘어가게 수정
        return userService.login(user);
    }

    @GetMapping("transcription/{userId}")
    public List<TransferTranscription> getUserTranscription(@PathVariable Long userId){
        return transcriptionService.getUserTranscription(userId);
    }

    @GetMapping("review/{userId}")
    public List<TransferReview> getUserReview(@PathVariable Long userId){
        return reviewService.getUserReview(userId);
    }

//    @GetMapping("friend/{userId}")
//    public TransferUser friend(@PathVariable Long userId){
//        return userService.findUser(userId);
//    }

    @GetMapping("record/{userId}/{year}")
    public MessageWithGrass getUserRecord(@PathVariable Long userId , @PathVariable Long year){
        return userService.getUserRecordCount(userId , year);
    }
}