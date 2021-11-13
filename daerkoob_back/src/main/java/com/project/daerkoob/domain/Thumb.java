package com.project.daerkoob.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "thumb")
public class Thumb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "thumb_id")
    private Long id;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "review_id")
    private Long reviewId;
    @Column(name = "transcription_id")
    private Long transcriptionId;
}
