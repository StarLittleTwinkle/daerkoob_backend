package com.project.daerkoob.service;

import com.project.daerkoob.domain.Friend;
import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.model.TransferUser;
import com.project.daerkoob.repository.FriendRepository;
import com.project.daerkoob.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    /*
    userId 로 넘어온다.
    그러면 findAll 을 통해서 일단 모든 user list 들이 다 반환이 될 것이다.
    거기서 해당 userId를 가진 친구의 친구들은 제외시켜야 한다.
    그러면 어떻게 할 수 있을까?
     */
    public List<User> findAll(Long userId){
        User findByUser = userRepository.findById(userId).get();
        return userRepository.findAll().stream().filter(
                user -> !friendRepository.existsByUserAndFriendIndex(findByUser , user.getId()) && user.getId() != userId)
                .collect(Collectors.toList());
    }

    public List<Friend> findByNickName(String nickName){
        return friendRepository.findByFriendNickName(nickName);
    }
    public List<Friend> ask(Long userId){
        return friendRepository.findByUser(userRepository.findById(userId).get());
    }

    public MessageWithList friendDelete(Long userId, Long friendId){
        User user = userRepository.findById(userId).get();
        user.setFriendCount(user.getFriendCount() - 1);

        //userFriendCount 감소 시켜주고 , save 하면서 , user 다시 받아낸다.
        user = userRepository.save(user);

        // 그리고 friend 에서 관계 없애주기
        friendRepository.deleteByUserAndFriendIndex(user , friendId);
        return new MessageWithList(user.getFriendCount() , new Message(true , "친구 삭제에 성공했습니다.") , List.of(userService.createTransferUser(user)));
   }
    public MessageWithList add(Long userId, Long friendId){ // 친구 추가 , 그냥 친구 추가하고 , user 정보 새로 반환하자.
        Message message = null;

        if (friendRepository.existsByUserAndFriendIndex(userRepository.findById(userId).get(),friendId)){
            message = new Message(false ,  "이미 친구입니다.");
        }

        else if(userId == friendId){
            message = new Message(false , "자신은 친구로 추가할 수 없습니다.");
        }

        if(message != null){ //이미 친구로 추가할 수 없다는 것이 정해졌을 때
            User user = userRepository.findById(userId).get();
            return new MessageWithList(user.getFriendCount() , message , List.of(userService.createTransferUser(user)));
        }

        User user = userRepository.findById(userId).get();

        user.setFriendCount(user.getFriendCount() + 1);

        user = userRepository.save(user); //친구 수 바꾸고 저장 (업데이트)

        friendRepository.save(createFriend(userId, friendId)); // 친구로 저장

        return new MessageWithList(user.getFriendCount(), new Message(true , "친구 등록에 성공했습니다."), new ArrayList<>(List.of(userService.createTransferUser(user))));
    }

    public Friend createFriend(Long userId , Long friendId){ //friend 객체를 만드는 함수
        Friend newFriend = new Friend();
        newFriend.setFriendIndex(friendId);
        newFriend.setFriendNickName(userRepository.findById(friendId).get().getNickName());
        newFriend.setUser(userRepository.findById(userId).get());
        return newFriend;
    }
}
