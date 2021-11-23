package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Book;
import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.domain.User;
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

    @GetMapping("judge/{isbn}")
    public Boolean getJudge(@PathVariable String isbn) throws Exception{ //false 는 필사 존재 x , true 는 필사 존재 o (필사 보기 가능)
        Optional<Book> book = bookService.findBook(isbn);
        Book resultBook = book.orElse(null);
        if(resultBook == null){
            return false;
        }
        List<Review> reviewList = reviewService.getBookReview(resultBook.getId());
        if(reviewList.size() == 0){
            return false;
        }
        return true;
    }

    @GetMapping("inquiry/{isbn}") //해당 책에 대한 필사내용 조회
    public List<Review> inquiry(@PathVariable String isbn) throws Exception{
        Optional<Book> book = bookService.findBook(isbn);
        return reviewService.getBookReview(book.get().getId());
    }

    @GetMapping("register/{userId}/{isbn}/{reviewContent}/{score}") //guide line , 이제 그냥 isbn 넘겨주시면 가능합니다.
    public void getRegister(@PathVariable Long userId, @PathVariable String isbn, @PathVariable String reviewContent , @PathVariable Long score) throws Exception{
        bookService.save(bookService.createBook(isbn));
        Optional<Book> book = bookService.findBook(isbn);
        reviewService.save(reviewService.createDto(userId, book.get().getId() ,  score , reviewContent));
    }


    @PostMapping("register")
    public void register(Long userId, String isbn , String reviewContent , Long score) throws Exception{
        bookService.save(bookService.createBook(isbn));
        Optional<Book> book = bookService.findBook(isbn);
        reviewService.save(reviewService.createDto(userId, book.get().getId() ,  score , reviewContent));
    }
}
