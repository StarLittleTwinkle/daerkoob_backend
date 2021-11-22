package com.project.daerkoob.service;

import com.project.daerkoob.repository.ThumbRepository;
import org.springframework.stereotype.Service;

@Service
public class ThumbService {

    private ThumbRepository thumbRepository;

    public ThumbService(ThumbRepository thumbRepository){
        this.thumbRepository = thumbRepository;
    }
}
