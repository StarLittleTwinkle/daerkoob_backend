package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Friend;
import com.project.daerkoob.domain.Message;
import com.project.daerkoob.service.FriendService;
import com.project.daerkoob.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("friend")
public class FriendController {

    private FriendService friendService;
    private UserService userService;

    public FriendController(FriendService friendService , UserService userService){
        this.friendService = friendService;
        this.userService = userService;
    }

    @GetMapping("add/{userId}/{friendId}") //guide line
    public Message getAdd(@PathVariable Long userId , @PathVariable Long friendId){
        return friendService.add(userId, friendId);
    } //자신은 친구로 안되게 그리고 친구조회도 훨씬 쉽게

    @PostMapping("add")
    public Message add(Long userId , Long friendId){ //없는 친구면 그냥 추가하면 됨 , 근데 만약 이미 있으면?
        return friendService.add(userId , friendId);
    }

    @GetMapping("ask/{userId}") //guide line 근데 이거는 그냥 지영님이 user 정보 얻을 때 friend까지 다가서 상관없을 듯해영
    public List<Friend> getAsk(@PathVariable Long userId){
        return userService.findUser(userId).getFriends();
    }

    @PostMapping("ask")
    public List<Friend> ask(Long userId){
        return userService.findUser(userId).getFriends();
    }
}
