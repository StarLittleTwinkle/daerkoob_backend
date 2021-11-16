package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Friend;
import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.service.FriendService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("friend")
public class FriendController {
    private FriendService friendService;

    public FriendController(FriendService friendService){
        this.friendService = friendService;
    }

    @GetMapping("{userId}/{friendId}") //guide line
    public Message getAdd(@PathVariable Long userId , @PathVariable Long friendId){
        System.out.println("call the getAdd");
        return friendService.add(userId, friendId);
    }

    @PostMapping("add")
    public Message add(Long userId , Long friendId){ //없는 친구면 그냥 추가하면 됨 , 근데 만약 이미 있으면?
        return friendService.add(userId , friendId);
    }

    @GetMapping("{userId}") //guide line
    public List<Friend> getAsk(@PathVariable Long userId){
        return friendService.ask(userId);
    }

    @PostMapping("ask")
    public List<Friend> ask(Long userId){
        return friendService.ask(userId);
    }
}
