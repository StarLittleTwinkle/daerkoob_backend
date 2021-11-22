package com.project.daerkoob.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "friend")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "friend_index")
    private Long friendIndex;
    @ManyToOne
    @JoinColumn(name = "user_index")
    private User user;
}
