package com.project.daerkoob.service;

import com.project.daerkoob.domain.Friend;
import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.repository.FriendRepository;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    FriendRepository friendRepository;
    UserRepository userRepository;

    public FriendService(FriendRepository friendRepository , UserRepository userRepository){
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
    }

    public List<Friend> ask(Long userId){
        return friendRepository.findByUserIndex(userId);
    }

    public Message add(Long userId, Long friendId){ // 친구 추가
        System.out.println("call the add");
        if (friendRepository.existsByUserIndexAndFriendIndex(userId,friendId)){
            return new Message(false , "이미 친구입니다.");
        }
        Optional<User> findById = userRepository.findById(userId);
        User resultUser = findById.get();
        System.out.println(resultUser);
        resultUser.setFriend(resultUser.getFriend() + 1);
        friendRepository.save(createFriend(userId, friendId)); // 친구로 저장
        return new Message(true , "친구 등록에 성공했습니다.");
    }

    public Friend createFriend(Long userId , Long friendId){ //friend 객체를 만드는 함수
        System.out.println("call the createFriend");
        Friend newFriend = new Friend();
        Optional<User> findById = userRepository.findById(friendId);
        User resultFriend = findById.get();
        newFriend.setFriendIndex(resultFriend.getId());
        newFriend.setNickName(resultFriend.getNickName());
        newFriend.setUserIndex(userId);
        return newFriend;
    }
}
