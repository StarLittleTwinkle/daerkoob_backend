package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Book;
import com.project.daerkoob.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("find/{isbn}") //글쓰기 및 책 내용보기를 클릭하면 넘어오는 정보
    public Book getClick(@PathVariable String isbn) throws Exception{
        return bookService.createBook(isbn);
    }

    @PostMapping("find")
    public List<Book> findBook(String title ,String display) throws Exception {
        List<Book> bookList = bookService.getBook(title , display);
        return bookList;
    }

    @GetMapping("best")
    public List<Book> bestBook(){
        List<Book> bestBookList = bookService.getBest();
        return bestBookList;
    }
}
