package com.project.daerkoob.controller;

import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.service.FriendService;
import com.project.daerkoob.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("register/{userId}/{friendId}") //guide line
    public MessageWithList getAdd(@PathVariable Long userId , @PathVariable Long friendId){
        return friendService.add(userId, friendId);
    } //자신은 친구로 안되게 그리고 친구조회도 훨씬 쉽게

    @PostMapping("register") //친구리스트랑 boolean 같이넘어오게 ,
    public MessageWithList add(Long userId , Long friendId){ //없는 친구면 그냥 추가하면 됨 , 근데 만약 이미 있으면?
        return friendService.add(userId , friendId);
    }

    @GetMapping("delete/{userId}/{friendId}")
    public MessageWithList getDelete(@PathVariable Long userId , @PathVariable Long friendId){
        System.out.println("userId : " + userId + " friendId : " + friendId);
        return friendService.friendDelete(userId, friendId);
    }

    @DeleteMapping("delete")
    public MessageWithList delete(Long userId , Long friendId){
        return friendService.friendDelete(userId, friendId);
    }
//    @GetMapping("ask/{userId}") //guide line 근데 이거는 그냥 지영님이 user 정보 얻을 때 friend까지 다가서 상관없을 듯해영
//    public List<Friend> getAsk(@PathVariable Long userId){
//        return userService.findUser(userId).getFriends();
//    }
//
//    @PostMapping("ask")
//    public List<Friend> ask(Long userId){
//        return userService.findUser(userId).getFriends();
//    }
}
