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
    @GetMapping("{isbn}") //guide line , 해당 book의 isbn을 보내면 해당 필사 내용이 Transcription의 형태로 다 넘어옴
    public List<Transcription> getClick(@PathVariable String isbn){
        List<Transcription> transcriptions = new ArrayList<Transcription>();
        if (bookService.existsBook(isbn)) {
            transcriptions = transcriptionService.getTranscription(bookService.getBookId(isbn));
        }
        return transcriptions; //별점도 담겨 있음
    }
    @PostMapping("click") //책을 눌렀을 때 없으면 그냥 아무일도 안 일어남
    public List<Transcription> click(String isbn){
        System.out.println("call the click method");
        List<Transcription> transcriptions = new ArrayList<Transcription>();
        if (bookService.existsBook(isbn)) {
            transcriptions = transcriptionService.getTranscription(bookService.getBookId(isbn));
        }
        return transcriptions; //별점도 담겨 있음
    }

    @GetMapping("{userId}/{title}/{author}/{publisher}/{pubdate}/{isbn}/{image}/{description}/{transcriptionContent}") //guide line , 없는 user_index 넘기면 에러나니까 조심해주세요
    public void getRegister(@PathVariable Long userId ,@PathVariable String title ,@PathVariable String author , @PathVariable String publisher , @PathVariable String pubdate ,@PathVariable String isbn , @PathVariable String image , @PathVariable String description , @PathVariable String transcriptionContent){
        bookService.save(bookService.createBook(title , author , publisher , pubdate , isbn , image , description));
        Book book = bookService.findBook(isbn);
        User user = userService.findUser(userId);
        transcriptionService.save(transcriptionService.createDto(userId, book.getId(), book.getTitle() ,user.getNickName() ,  transcriptionContent));
    } //register guide line

    @PostMapping("register") //책에 대한 필사 내용을 적고 submit을 눌렀을 때
    public Message register(Long userId, String title , String author , String publisher , String pubdate , String isbn , String image , String description , String transcriptionContent){
        bookService.save(bookService.createBook(title, author, publisher , pubdate , isbn, image , description));
        Book book = bookService.findBook(isbn);
        User user = userService.findUser(userId);
        return transcriptionService.save(transcriptionService.createDto(userId, book.getId(), book.getTitle() ,user.getNickName() ,  transcriptionContent));
    }
}
