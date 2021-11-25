package com.project.daerkoob.model;

import com.project.daerkoob.domain.User;
import lombok.Data;

import com.project.daerkoob.domain.Book;
import java.time.LocalDate;

@Data
public class TransferTranscription {
    Book book;
    User user;
    Long id;
    String content;
    Long thumbCount;
    LocalDate registerDate;
    boolean thumbJudge;
}
