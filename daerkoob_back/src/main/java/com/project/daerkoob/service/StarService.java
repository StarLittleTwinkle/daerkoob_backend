package com.project.daerkoob.service;

import com.project.daerkoob.repository.StarRepository;
import org.springframework.stereotype.Service;

@Service
public class StarService {
    StarRepository starRepository;
    public StarService(StarRepository starRepository){
        this.starRepository = starRepository;
    }
}
