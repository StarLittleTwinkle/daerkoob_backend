package com.project.daerkoob.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "description")
    private String description;

    @Column(name = "transcription_count")
    private Long transcriptionCount;

    @Column(name = " review_count")
    private Long reviewCount;

    @Column(name = "star")
    private Double star;

    @Column(name = "star_count")
    private Long starCount;

    public Book(String title , String image , String publisher , String pubdate ,
                String isbn , String description , Long transcriptionCount , Long reviewCount , Double star , Long starCount){
        this.title = title;
        this.image = image;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.isbn = isbn;
        this.description = description;
        this.transcriptionCount = transcriptionCount;
        this.reviewCount = reviewCount;
        this.star = star;
        this.starCount = starCount;
    }
}
