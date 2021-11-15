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

    public List<Friend> ask(User user){
        return friendRepository.findByUserIndex(user.getId());
    }

    public Message add(User user, User friend){ // 친구 추가
        if (friendRepository.existsByUserIndexAndFriendIndex(user.getId(),friend.getId())){
            return new Message(false , "이미 친구입니다.");
        }
        Optional<User> findById = userRepository.findById(user.getId());
        User resultUser = findById.get();
        resultUser.setFriend(resultUser.getFriend() + 1);
        friendRepository.save(createFriend(user, friend)); // 친구로 저장
        return new Message(true , "친구 등록에 성공했습니다.");
    }

    public Friend createFriend(User user , User friend){ //friend 객체를 만드는 함수
        Friend newFriend = new Friend();
        newFriend.setFriendIndex(friend.getId());
        newFriend.setNickName(friend.getNickName());
        newFriend.setUserIndex(user.getId());
        return newFriend;
    }
}
