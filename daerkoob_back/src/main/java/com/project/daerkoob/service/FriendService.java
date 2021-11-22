package com.project.daerkoob.service;

import com.project.daerkoob.repository.FriendRepository;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    private FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository){
        this.friendRepository = friendRepository;
    }
}
