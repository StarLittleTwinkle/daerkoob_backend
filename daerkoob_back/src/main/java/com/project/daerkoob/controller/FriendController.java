package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Friend;
import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 이미 친구인 사람만 제거한 후 반환
    @GetMapping("{userId}")
    public MessageWithList findAll(@PathVariable Long userId){
        List<User> result = friendService.findAll(userId);
        return new MessageWithList(new Long(result.size()), new Message(true , "유저 목록입니다.") ,(ArrayList)result);
    }

    @PostMapping("find/{nickname}")
    public MessageWithList findByNickName(@PathVariable(name = "nickname") String nickName){
        List<Friend> result = friendService.findByNickName(nickName);
        return new MessageWithList(new Long(result.size()) , new Message(true , "검색하신 유저 목록입니다.") , (ArrayList)result);
    }

    @PostMapping("register") //친구리스트랑 boolean 같이넘어오게 ,
    public MessageWithList add(Long userId , Long friendId){ //없는 친구면 그냥 추가하면 됨 , 근데 만약 이미 있으면?
        return friendService.add(userId , friendId);
    }


    @DeleteMapping("delete")
    public MessageWithList delete(Long userId , Long friendId){
        return friendService.friendDelete(userId, friendId);
    }


    /*
    내 친구면 , 이미 친구
    닉네임으로도 검색할 수 있도록

    그럴려면 일단 , friend 를 nickName 으로 find 할 수 있어야 함 , 그리고 register 과 엮든가 해야 할 듯

     */
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
