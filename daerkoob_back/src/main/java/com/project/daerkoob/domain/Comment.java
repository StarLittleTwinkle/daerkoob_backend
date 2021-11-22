package com.project.daerkoob.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @Column(name = " review_id")
    private Long reviewId;
    @Column(name = " nested_comment")
    private Long nestedComment;
    @Column(name = " content")
    private String content;
    @Column(name = "user_id")
    private Long userid;
    @Column(name = "thumb_count")
    private Long thumbCount;
}
