package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.model.TransferUser;
import com.project.daerkoob.service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    public Message signUp(User user, String confirmPassword){
        return userService.signUp(user, confirmPassword);
    }

    @PostMapping("login")
    public TransferUser login(User user) { //친구 목록까지 넘어가게 수정
        return userService.login(user);
    }

//    @GetMapping("friend/{userId}")
//    public TransferUser friend(@PathVariable Long userId){
//        return userService.findUser(userId);
//    }
}