package com.project.daerkoob.domain;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user")
public class User {

    @Id @Column(name = "user_index") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
//    @NotEmpty
//    @Size(min = 9 , max = 15)
    @Column(name = "user_id")
    private String userId;

    @Column(name = "name")
//    @NotEmpty
//    @NotNull
    private String name;

    @Column(name = "nick_name")
//    @NotEmpty
//    @NotNull
    private String nickName;

    @Column(name = "password")
//    @NotEmpty
//    @NotNull
//    @Size(min = 9 , max = 15)
    private String password;

    @Column(name = "birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @Column(name = "admin")
    private String admin;

    @Column(name = "friend")
    private Long friend; // String 으로 관리하면서 배열 형태로 관리하다가 파싱해서 친구 목록 출력
}
