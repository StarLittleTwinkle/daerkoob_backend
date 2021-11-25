package com.project.daerkoob.model;

import com.project.daerkoob.domain.User;
import lombok.Data;

import com.project.daerkoob.domain.Book;
import java.time.LocalDate;

@Data
public class TransferReview {
    Book book;
    User user;
    Long id;
    String content;
    Long thumbCount;
    Long score;
    LocalDate registerDate;
    boolean thumbJudge;
}
