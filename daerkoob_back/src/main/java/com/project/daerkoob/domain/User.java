package com.project.daerkoob.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user")
public class User {

    @Id @Column(name = "user_index") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "name")
    private String name;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "password")
    private String password;
    @Column(name = "birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    @Column(name = "friend_count")
    private Long friendCount;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Friend> friends;
    @Column(name = "review_count")
    private Long reviewCount;
    @Column(name = "transcription_count")
    private Long transcriptionCount;
}
