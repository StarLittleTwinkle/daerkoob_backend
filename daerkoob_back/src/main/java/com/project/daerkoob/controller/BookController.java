package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Book;
import com.project.daerkoob.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("find")
    public List<Book> postFindBook(String title ,String display) throws Exception {
        List<Book> bookList = bookService.getBook(title , display);
        return bookList;
    }
}
