package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.service.BookService;
import com.project.daerkoob.service.TranscriptionService;
import com.project.daerkoob.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.project.daerkoob.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("transcription")
public class TranscriptionController {

    private TranscriptionService transcriptionService;
    private BookService bookService;
    private UserService userService;

    public TranscriptionController(TranscriptionService transcriptionService , BookService bookService , UserService userService){
        this.transcriptionService = transcriptionService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("judge/{isbn}")
    public Boolean getJudge(String isbn) throws Exception{ //false 는 필사 존재 x , true 는 필사 존재 o (필사 보기 가능)
        Optional<Book> book = bookService.findBook(isbn);
        Book resultBook = book.orElse(null);
        if(resultBook == null){
            return false;
        }
        List<Transcription> transcriptionList = transcriptionService.getTranscription(resultBook.getId());
        if(transcriptionList.size() == 0){
            return false;
        }
        return true;
    }

    @GetMapping("inquiry/{isbn}") //해당 책에 대한 필사내용 조회
    public List<Transcription> inquiry(@PathVariable String isbn) throws Exception{
        Optional<Book> book = bookService.findBook(isbn);
        return transcriptionService.getTranscription(book.get().getId());
    }

    @GetMapping("{userId}/{isbn}/{transcriptionContent}") //guide line , 이제 그냥 isbn 넘겨주시면 가능합니다.
    public void getRegister(@PathVariable Long userId, @PathVariable String isbn, @PathVariable String transcriptionContent) throws Exception{
        bookService.save(bookService.createBook(isbn));
        Optional<Book> book = bookService.findBook(isbn);
        User user = userService.findUser(userId);
        transcriptionService.save(transcriptionService.createDto(userId, book.get().getId(), book.get().getTitle() ,user.getNickName() ,  transcriptionContent));
    }

    @PostMapping("register") //책에 대한 필사 내용을 적고 submit을 눌렀을 때
    public Message register(Long userId, String isbn , String transcriptionContent) throws Exception{
        System.out.println("call the transcription register");
        bookService.save(bookService.createBook(isbn));
        Optional<Book> book = bookService.findBook(isbn);
        User user = userService.findUser(userId);
        return transcriptionService.save(transcriptionService.createDto(userId, book.get().getId(), book.get().getTitle() ,user.getNickName() ,  transcriptionContent));
    }
}
