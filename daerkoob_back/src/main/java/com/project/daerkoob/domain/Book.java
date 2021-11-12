package com.project.daerkoob.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author ")
    private String author;
    @Column(name = "image")
    private String image;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "pubdate")
    private String pubdate;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "thumb")
    private Long thumb;
    @Column(name = "star")
    private Double star;
}
