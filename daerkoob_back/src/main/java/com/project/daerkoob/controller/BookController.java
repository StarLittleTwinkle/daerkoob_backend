package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Book;
import com.project.daerkoob.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("book")
public class BookController {

    private final BookService bookService;

    //글쓰기 및 책 내용보기를 클릭하면 넘어오는 정보
    @GetMapping("find/{isbn}")
    public Book getClick(@PathVariable String isbn) throws Exception{
        return bookService.createBook(isbn);
    }

    @PostMapping("find")
    public List<Book> findBook(String title ,String display) throws Exception {
        List<Book> bookList = bookService.getBook(title , display);
        return bookList;
    }

    @GetMapping("count")
    public Long countBook(){
        return bookService.countAll();
    }

    @GetMapping("best")
    public List<Book> bestBook(){
        List<Book> bestBookList = bookService.getBest();
        return bestBookList;
    }
}
