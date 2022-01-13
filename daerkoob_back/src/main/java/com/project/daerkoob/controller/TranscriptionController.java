package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.model.CountWithList;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.service.BookService;
import com.project.daerkoob.service.TranscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.project.daerkoob.domain.Book;
import java.util.Optional;

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

    @GetMapping("count")
    public Long countTranscription(){
        return transcriptionService.countAll();
    }

    @GetMapping("judge/{isbn}")
    public Boolean getJudge(@PathVariable String isbn){ //false 는 필사 존재 x , true 는 필사 존재 o (필사 보기 가능)
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

    @GetMapping("inquiry/{userId}/{isbn}") //해당 책에 대한 필사내용 조회
    public CountWithList inquiry(@PathVariable Long userId , @PathVariable String isbn){
        return transcriptionService.getBookTranscriptionOfCountWithList(userId , isbn);
    }

//    @GetMapping("register/{userId}/{isbn}/{transcriptionContent}") //guide line , 이제 그냥 isbn 넘겨주시면 가능합니다.
//    public boolean getRegister(@PathVariable Long userId, @PathVariable String isbn, @PathVariable String transcriptionContent) throws Exception{
//        bookService.save(bookService.createBook(isbn) , 2L);
//        Optional<Book> book = bookService.findBook(isbn);
//        transcriptionService.save(transcriptionService.createDto(userId, book.get().getId() ,  transcriptionContent));
//        return true;
//    }

    @PostMapping("register")
    public Message register(Long userId, String isbn , String transcriptionContent) throws Exception{
        if(!transcriptionContent.isBlank()) {
            bookService.save(bookService.createBook(isbn), 2L);
            Optional<Book> book = bookService.findBook(isbn);
            transcriptionService.save(transcriptionService.createDto(userId, book.get().getId(), transcriptionContent));
            return new Message(true , "입력에 성공하셨습니다.");
        }else{
            return new Message(false ,"다시 입력해주세요.");
        }
    }

    @GetMapping("delete/{userId}/{transcriptionId}")
    public MessageWithList getDelete(@PathVariable Long userId , @PathVariable Long transcriptionId){
        return transcriptionService.transcriptionDelete(userId, transcriptionId);
    }

    @DeleteMapping("delete")
    public MessageWithList delete(Long userId, Long transcriptionId){
        return transcriptionService.transcriptionDelete(userId, transcriptionId);
    }

    @GetMapping("recent")
    public List<Transcription> getRecentTranscription(){
        return transcriptionService.getRecentTranscription();
    }
}
