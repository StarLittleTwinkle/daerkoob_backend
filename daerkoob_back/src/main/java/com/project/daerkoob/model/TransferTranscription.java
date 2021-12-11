package com.project.daerkoob.model;

import com.project.daerkoob.domain.User;
import lombok.Data;

import com.project.daerkoob.domain.Book;
import java.time.LocalDateTime;

@Data
public class TransferTranscription {
    private Book book;
    private User user;
    private Long id;
    private String content;
    private Long thumbCount;
    private LocalDateTime registerDate;
    private boolean thumbJudge;
}
