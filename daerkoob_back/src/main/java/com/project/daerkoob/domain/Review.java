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
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(name = "thumb_count")
    private Long thumbCount;
    @Column(name = "content")
    private String content;
    @Column(name = "score")
    private Long score;
    @Column(name = "register_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;

}
