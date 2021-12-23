package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.model.Grass;
import com.project.daerkoob.model.TransferReview;
import com.project.daerkoob.model.TransferTranscription;
import com.project.daerkoob.model.TransferUser;
import com.project.daerkoob.service.ReviewService;
import com.project.daerkoob.service.TranscriptionService;
import com.project.daerkoob.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;
    private TranscriptionService transcriptionService;
    private ReviewService reviewService;

    public UserController(UserService userService , TranscriptionService transcriptionService , ReviewService reviewService) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.transcriptionService = transcriptionService;
    }

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
    public List<Grass> getUserRecord(@PathVariable Long userId , @PathVariable Long year){
        return userService.getUserRecordCount(userId , year);
    }
}