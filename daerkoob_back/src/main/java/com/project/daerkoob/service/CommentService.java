package com.project.daerkoob.service;

import com.project.daerkoob.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }
}
