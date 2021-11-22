package com.project.daerkoob.controller;

import com.project.daerkoob.service.FriendService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("friend")
public class FriendController {

    private FriendService friendService;

    public FriendController(FriendService friendService){
        this.friendService = friendService;
    }
}
