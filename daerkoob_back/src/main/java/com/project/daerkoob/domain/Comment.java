package com.project.daerkoob.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Column(name = "register_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd-hh-mm-ss")
    private LocalDateTime registerDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;
}
