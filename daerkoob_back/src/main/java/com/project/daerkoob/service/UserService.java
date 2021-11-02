package com.project.daerkoob.service;

import com.project.daerkoob.domain.User;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }
    public Optional<User> findByNickName(String nickName){
        return userRepository.findByNickName(nickName);
    }
}
