package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Book;
import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Review;
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

    @GetMapping("judge/{isbn}")
    public Boolean getJudge(@PathVariable String isbn) throws Exception{ //false 는 필사 존재 x , true 는 필사 존재 o (필사 보기 가능)
        Optional<Book> book = bookService.findBook(isbn); // book 을 찾아 봄
        Book resultBook = book.orElse(null);
        if(resultBook == null){ // 해당 book 이 있는지 검사
            return false;
        }
        return reviewService.getReview(resultBook.getId()); // 있으면 true , 없으면 false;
    }

    @GetMapping("inquiry/{userId}/{isbn}/{pageNumber}") //해당 책에 대한 리뷰내역 조회
    public MessageWithList inquiry(@PathVariable Long userId , @PathVariable String isbn , @PathVariable Long pageNumber) throws Exception{
        Optional<Book> book = bookService.findBook(isbn);
        return reviewService.getMessageWithListOfBookReview(userId, book.get().getId() , pageNumber);
    }


    @PostMapping("register")
    public Message register(Long userId, String isbn , String reviewContent , Double score) throws Exception{
        if(reviewContent.isBlank()) return new Message(false , "다시 입력해주세요.");
        bookService.save(bookService.createBook(isbn) , 1L);
        Optional<Book> book = bookService.findBook(isbn);
        reviewService.save(reviewService.createDto(userId, book.get().getId() ,  score , reviewContent));
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
