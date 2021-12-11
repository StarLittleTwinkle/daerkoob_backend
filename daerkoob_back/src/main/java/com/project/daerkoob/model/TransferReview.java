package com.project.daerkoob.model;

import com.project.daerkoob.domain.Comment;
import com.project.daerkoob.domain.User;
import lombok.Data;

import com.project.daerkoob.domain.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TransferReview {
    private Book book;
    private User user;
    private Long id;
    private String content;
    private Long thumbCount;
    private Double score;
    private LocalDateTime registerDate;
    private boolean thumbJudge;
}
