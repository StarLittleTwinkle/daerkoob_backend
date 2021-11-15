package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Friend;
import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.repository.FriendRepository;
import com.project.daerkoob.service.FriendService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("friend")
public class FriendController {
    private FriendService friendService;
    private FriendRepository friendRepository;

    public FriendController(FriendService friendService , FriendRepository friendRepository){
        this.friendService = friendService;
        this.friendRepository = friendRepository;
    }

    @PostMapping("add")
    public Message add(User user , User friend){ //없는 친구면 그냥 추가하면 됨 , 근데 만약 이미 있으면?
        return friendService.add(user , friend);

    }
    @PostMapping("ask")
    public List<Friend> ask(User user){
        return friendService.ask(user);
    }
}
