package com.project.daerkoob.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
//    @Column(name = "nested_comment")
//    private Long nestedComment;
    @ManyToOne
    @JoinColumn(name = "nested_comment")
    private Comment comment; //comment가 자신을 참조한다
    @OneToMany(mappedBy = "comment")
    @JsonIgnore
    @ToString.Exclude
    private List<Comment> comments;
    @Column(name = " content")
    private String content;
    @Column(name = "thumb_count")
    private Long thumbCount;
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;
}
