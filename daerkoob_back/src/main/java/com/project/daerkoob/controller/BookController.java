package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Book;
import com.project.daerkoob.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("find/{title}/{display}")
    public List<Book> getFindBook(@PathVariable String title , @PathVariable String display) throws Exception {
        List<Book> bookList = bookService.getBook(title , display);
        return bookList;
    }

    @PostMapping("find")
    public List<Book> findBook(String title ,String display) throws Exception {
        List<Book> bookList = bookService.getBook(title , display);
        return bookList;
    }
}
