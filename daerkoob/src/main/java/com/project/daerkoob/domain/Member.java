package com.project.daerkoob.domain;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Table(name = "member")
public class Member {
    @Id @Column(name = "member_index") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "name")
    private String name;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "password")
    private String password;
    @Column(name = "birth")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birth;
    @Column(name = "admin")
    private String admin;
    @Column(name = "friend")
    private String friend; // String 으로 관리하면서 배열 형태로 관리하다가 파싱해서 친구 목록 출력
}
