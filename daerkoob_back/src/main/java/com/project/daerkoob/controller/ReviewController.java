package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Book;
import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.service.BookService;
import com.project.daerkoob.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("review")
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;

    @GetMapping("count")
    public Long countReview(){
        return reviewService.countAll();
    }

    // 해당 기능의 리뷰 개수도 같이 넘기기
    @GetMapping("judge/{isbn}")
    public MessageWithList getJudge(@PathVariable String isbn) throws Exception{ //false 는 필사 존재 x , true 는 필사 존재 o (필사 보기 가능)
        Optional<Book> book = bookService.findBook(isbn); // book 을 찾아 봄
        Book resultBook = book.orElse(null);

        if(resultBook == null){ // 해당 book 이 있는지 검사
            return new MessageWithList(0L , new Message(false , "리뷰가 없습니다."));
        }

        if(!reviewService.getReview(resultBook.getId())){ // 필사가 쓰였다가 지워진 경우
            return new MessageWithList(0L , new Message(false , "리뷰가 없습니다."));
        }

        return new MessageWithList(reviewService.getReviewCount(resultBook) , new Message(true , "리뷰가 있습니다.")); // 있으면 true , 없으면 false;
    }

    @GetMapping("inquiry/{userId}/{isbn}/{pageNumber}") //해당 책에 대한 리뷰내역 조회
    public MessageWithList inquiry(@PathVariable Long userId , @PathVariable String isbn , @PathVariable Long pageNumber) throws Exception{
        Book book = bookService.findBook(isbn).orElse(null);

        // 아직 책이 등록되는 중이라 발생하는 예외 방지
        if(!(book == null)) return reviewService.getMessageWithListOfBookReview(userId, book.getId() , pageNumber);
        else return new MessageWithList(0L , new Message(false , "리뷰가 없습니다.") , null);
    }

    @PostMapping("register")
    public Message register(Long userId, String isbn , String content , Double score) throws Exception{
        if(content == null || content.isBlank()) return new Message(false , "다시 입력해주세요.");
        bookService.save(bookService.createBook(isbn) , 1L);
        Optional<Book> book = bookService.findBook(isbn);
        reviewService.save(reviewService.createDto(userId, book.get().getId() ,  score == null ? 0 : score , content));
        return new Message(true , "리뷰 등록에 성공하셨습니다.");
    }

    @DeleteMapping("delete")
    public MessageWithList delete(Long reviewId , Long userId){ //review id만 주면은 삭제가 가능하도록 , 근데 이게 이 유저의 댓글인지를 알아야하니까 userId를 받는다
        //그러면서 delete하면서 다시 해당 book에 대한 정보 받아올 수 있도록 bookId까지 받아서옴
        return reviewService.reviewDelete(reviewId , userId);
    }

    @GetMapping("recent")
    public List<Review> getRecentTranscription(){
        return reviewService.getRecentReview();
    }
}
