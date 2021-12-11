package com.project.daerkoob.model;

import com.project.daerkoob.domain.Comment;
import com.project.daerkoob.domain.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TransferComment {
    private Long id;
    private String content;
    private User writer;
    private Long thumbCount;
    private Boolean thumbJudge;
    private LocalDateTime registerDate;
    private List<Comment> comments;
}
