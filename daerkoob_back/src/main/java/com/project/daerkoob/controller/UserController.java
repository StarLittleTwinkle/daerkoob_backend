package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.repository.UserRepository;
import com.project.daerkoob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService , UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("signup")
    public Message signUp(User user, String confirmPassword){
        return userService.signUp(user, confirmPassword);
    }

    @PostMapping("login")
    public boolean login(User user) {
        return userService.login(user);
    }
}