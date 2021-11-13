package com.project.daerkoob.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "thumb")
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "star_id")
    private Long id;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = " user_id")
    private Long userId;
    @Column(name = "score")
    private Long score;
    @Column(name = "review_id")
    private Long reviewId;
    @Column(name = "transcription_id")
    private Long transcriptionId;
}
