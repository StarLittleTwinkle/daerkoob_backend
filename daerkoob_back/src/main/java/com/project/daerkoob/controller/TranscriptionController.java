package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.service.BookService;
import com.project.daerkoob.service.TranscriptionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.daerkoob.domain.Book;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("transcription")
public class TranscriptionController {

    private TranscriptionService transcriptionService;
    private BookService bookService;

    public TranscriptionController(TranscriptionService transcriptionService , BookService bookService){
        this.transcriptionService = transcriptionService;
        this.bookService = bookService;
    }
    @PostMapping("click") //책을 눌렀을 때 없으면 그냥 아무일도 안 일어남
    public List<Transcription> click(Book book){
        List<Transcription> transcriptions = new ArrayList<Transcription>();
        if (bookService.existsBook(book)) {
            transcriptions = transcriptionService.getTranscription(bookService.getBookId(book));
        }
        return transcriptions;
    }


    @PostMapping("register") //책에 대한 필사 내용을 적고 submit을 눌렀을 때
    public void register(Long userId, Book book , String transcriptionContent){
        bookService.save(book);
        transcriptionService.save(transcriptionService.createDto(userId, book.getId(), transcriptionContent));
    }
}
