package com.project.daerkoob.controller;

import java.util.List;

import com.project.daerkoob.model.TransferComment;
import com.project.daerkoob.service.CommentService;
import com.project.daerkoob.service.ReviewService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("comment")
public class CommentController {

    private CommentService commentService;
    private ReviewService reviewService;

    public CommentController(CommentService commentService, ReviewService reviewService) {
        this.commentService = commentService;
        this.reviewService =reviewService;
    }

    @GetMapping("register/review/{userId}/{reviewId}/{content}")
    public void getCommentOfReview(@PathVariable Long userId , @PathVariable Long reviewId, @PathVariable String content) { //guide line
        commentService.save(commentService.createCommentOfReviewDto(userId , reviewId , content));
    }

    @PostMapping("register/review")
    public void commentOfReview(Long userId , Long reviewId, String content) {
        commentService.save(commentService.createCommentOfReviewDto(userId , reviewId , content));
    }

    @GetMapping("inquiry/review/{reviewId}") //이거 하면 그냥 review에 달린 댓글들이 다 나옴 , 대댓글에 대댓글은 안나옴, youtube나 이런 곳 보니까 다 막아놓은 걸 보아하니 , 우리도 막아야할 듯
    public List<TransferComment> inquiryCommentOfReview(@PathVariable Long reviewId){
        System.out.println("call the inquiryReview Method");
        return reviewService.getCommentOfReview(reviewId);
    }

    @GetMapping("register/nested/{userId}/{commentId}/{content}") //자신의 댓글에다가 댓글을 다는 경우에는 자신의 테이블에 있는 레코드를 가르키게 됨 , 일단은 join 연산으로 구현할 생각은 못한 상태이다.
    public void getCommentOfComment(@PathVariable Long userId , @PathVariable Long commentId, @PathVariable String content) { //guide line
        commentService.save(commentService.createCommentOfCommentDto(userId ,commentId , content));
    }

    @PostMapping("register/nested")
    public void commentOfComment(Long userId , Long commentId, String content) {
        commentService.save(commentService.createCommentOfCommentDto(userId, commentId , content));
    }
}
