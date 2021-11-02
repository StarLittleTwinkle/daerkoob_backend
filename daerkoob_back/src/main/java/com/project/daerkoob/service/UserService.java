package com.project.daerkoob.service;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.repository.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository; //UserService가 userRepository 를 사용 가능하도록 dependency injection 을 추가
    }

    public Optional<User> findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    public Optional<User> findByNickName(String nickName){
        return userRepository.findByNickName(nickName);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public Message signUp(User user , String confirmPassword){
        if (user.getUserId() == null || user.getUserId().length() == 0) {
            return new Message(false, "아이디를 입력하세요.");
        } else if (user.getName() == null || user.getName().length() == 0) {
            return new Message(false, "이름을 입력하세요.");
        } else if (user.getNickName() == null || user.getNickName().length() == 0) {
            return new Message(false, "닉네임을 입력하세요.");
        } else if (user.getPassword() == null || user.getPassword().length() == 0) {
            return new Message(false, "비밀번호를 입력하세요.");
        } else if (confirmPassword == null || confirmPassword.length() == 0) {
            return new Message(false, "비밀번호를 한번 더 입력하세요.");
        } else if (user.getBirth() == null) {
            return new Message(false, "생일을 입력하세요.");
        } else if (user.getPassword().equals(confirmPassword)) {
            Optional<User> findUserId = userRepository.findByUserId(user.getUserId());
            Optional<User> findNickName = userRepository.findByNickName(user.getNickName());
            User findUserIdResult = findUserId.orElse(null);
            User findNickNameResult = findNickName.orElse(null);
            if(findUserIdResult == null && findNickNameResult == null) {
                userRepository.save(user);
                return new Message(true, "회원가입 성공");
            }
            else if(findUserIdResult != null) {
                return new Message(false, "이미 존재하는 아이디입니다.");
            }
            else{
                return new Message(false , "이미 존재하는 닉네임입니다.");
            }
        }
        else{
            return new Message(false, "비밀번호를 다시 입력해주세요");
        }
    }

    public boolean login(User user){
        Optional<User> resultUser = userRepository.findByUserId(user.getUserId());
        User result = resultUser.orElse(null);
        if(result == null) {
            return false;
        }
        else{
            if(result.getPassword().equals(user.getPassword())){
                return true;
            }
            else {
                return false;
            }
        }
    }
}
