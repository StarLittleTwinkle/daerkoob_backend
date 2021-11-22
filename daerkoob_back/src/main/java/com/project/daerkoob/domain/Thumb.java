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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumb_id")
    private Long id;
    @Column(name = "review_id")
    private Long reviewId;
    @Column(name = "transcription_id")
    private Long transcriptionId;
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "given_user_id")
    private Long givenUserId;
}
