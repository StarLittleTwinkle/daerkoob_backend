package com.project.daerkoob.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "book_category")
    private String category;
    @Column(name = "writer")
    private String writer;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "year")
    private Long year;
    @Column(name = "ibsn")
    private String ibsn;
    @Column(name = "like")
    private Long like;
    @Column(name = "star")
    private Double star;
}
