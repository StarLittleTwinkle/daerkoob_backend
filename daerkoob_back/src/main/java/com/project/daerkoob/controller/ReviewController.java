package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Book;
import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.model.TransferReview;
import com.project.daerkoob.service.BookService;
import com.project.daerkoob.service.ReviewService;
import com.project.daerkoob.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("review")
public class ReviewController {

    private ReviewService reviewService;
    private BookService bookService;
    private UserService userService;

    public ReviewController(ReviewService reviewService , BookService bookService , UserService userService){
        this.reviewService = reviewService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("count")
    public Long countReview(){
        return reviewService.countAll();
    }

    @GetMapping("judge/{isbn}")
    public Boolean getJudge(@PathVariable String isbn) throws Exception{ //false 는 필사 존재 x , true 는 필사 존재 o (필사 보기 가능)
        Optional<Book> book = bookService.findBook(isbn);
        Book resultBook = book.orElse(null);
        if(resultBook == null){
            return false;
        }
        List<Review> reviewList = reviewService.getReview(resultBook.getId());
        if(reviewList.size() == 0){
            return false;
        }
        return true;
    }

    @GetMapping("inquiry/{userId}/{isbn}") //해당 책에 대한 리뷰내역 조회
    public List<TransferReview> inquiry(@PathVariable Long userId , @PathVariable String isbn) throws Exception{
        Optional<Book> book = bookService.findBook(isbn);
        return reviewService.getBookReview(userId, book.get().getId());
    }

    @GetMapping("register/{userId}/{isbn}/{reviewContent}/{score}") //guide line , 이제 그냥 isbn 넘겨주시면 가능합니다.
    public boolean getRegister(@PathVariable Long userId, @PathVariable String isbn, @PathVariable String reviewContent , @PathVariable Double score) throws Exception{
        bookService.save(bookService.createBook(isbn) , 1L);
        Optional<Book> book = bookService.findBook(isbn);
        reviewService.save(reviewService.createDto(userId, book.get().getId() ,  score , reviewContent));
        return true;
    }


    @PostMapping("register")
    public boolean register(Long userId, String isbn , String reviewContent , Double score) throws Exception{
        bookService.save(bookService.createBook(isbn) , 1L);
        Optional<Book> book = bookService.findBook(isbn);
        reviewService.save(reviewService.createDto(userId, book.get().getId() ,  score , reviewContent));
        return true;
    }

    @GetMapping("delete/{reviewId}/{userId}")
    public MessageWithList getDelete(@PathVariable Long reviewId , @PathVariable Long userId){ //review id만 주면은 삭제가 가능하도록 , 근데 이게 이 유저의 댓글인지를 알아야하니까 userId를 받는다
        //그러면서 delete하면서 다시 해당 book에 대한 정보 받아올 수 있도록 bookId까지 받아서옴
        return reviewService.reviewDelete(reviewId , userId);
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
