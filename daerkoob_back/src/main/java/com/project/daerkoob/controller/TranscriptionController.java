package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.service.BookService;
import com.project.daerkoob.service.TranscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.project.daerkoob.domain.Book;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("transcription")
public class TranscriptionController {

    private final TranscriptionService transcriptionService;
    private final BookService bookService;

    @GetMapping("count")
    public Long countTranscription(){
        return transcriptionService.countAll();
    }

    // 필사 수도 같이 넘기기
    @GetMapping("judge/{isbn}")
    public MessageWithList getJudge(@PathVariable String isbn){ //false 는 필사 존재 x , true 는 필사 존재 o (필사 보기 가능)
        Optional<Book> book = bookService.findBook(isbn);
        Book resultBook = book.orElse(null);
        if(resultBook == null){ // 아얘 필사가 쓰였던 적이 없는 경우
            return new MessageWithList(0L , new Message(false , "필사가 없습니다."));
        }
        List<Transcription> transcriptionList = transcriptionService.getTranscription(resultBook.getId());
        if(transcriptionList.size() == 0){ // 필사가 쓰였다가 지워진 경우
            return new MessageWithList(0L , new Message(false , "필사가 없습니다."));
        }

        return new MessageWithList(transcriptionService.getTranscriptionCount(resultBook) , new Message(true , "필사가 있습니다."));
    }

    @GetMapping("inquiry/{userId}/{isbn}/{pageNumber}") //해당 책에 대한 필사내용 조회
    public MessageWithList inquiry(@PathVariable Long userId , @PathVariable String isbn , @PathVariable Long pageNumber){
        /*
        transcription 은 인피니티 하게 스크롤 되야함 , 그러면 그냥 계속 정보를 받으면서 pagination 된 정보만 넘기면 된다.
         */
        return transcriptionService.getBookTranscriptionOfCountWithList(userId , isbn, pageNumber);
    }

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

    @DeleteMapping("delete")
    public MessageWithList delete(Long userId, Long transcriptionId){
        return transcriptionService.transcriptionDelete(userId, transcriptionId);
    }

    @GetMapping("recent")
    public List<Transcription> getRecentTranscription(){
        return transcriptionService.getRecentTranscription();
    }
}
