package com.project.daerkoob.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transcription")
public class Transcription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transcription_id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "thumb")
    private Long thumb;
    @Column(name = "star")
    private Double star;
    @Column(name = "star_count")
    private Long starCount;
    @Column(name = "transcription")
    private String transcription;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "user_nick_name")
    private String userNickName;
    @Column(name = "register_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;
}
