package com.project.daerkoob.model;

import com.project.daerkoob.domain.Comment;
import com.project.daerkoob.domain.User;
import lombok.Data;

import com.project.daerkoob.domain.Book;
import java.time.LocalDate;
import java.util.List;

@Data
public class TransferReview {
    Book book;
    User user;
    Long id;
    String content;
    Long thumbCount;
    Double score;
    LocalDate registerDate;
    boolean thumbJudge;
}
