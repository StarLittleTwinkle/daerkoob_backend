package com.project.daerkoob.service;

import com.project.daerkoob.domain.User;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
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
}
