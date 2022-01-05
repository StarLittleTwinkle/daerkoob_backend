package com.project.daerkoob.controller;

import java.util.List;

import com.project.daerkoob.model.MessageWithList;
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

    /*
    -- 수정할 사항
    1. review에 몇개의 댓글이 달렸는지 나올 수 있도록
     */
    @GetMapping("register/review/{userId}/{reviewId}/{content}")
    public void getCommentOfReview(@PathVariable Long userId , @PathVariable Long reviewId, @PathVariable String content) { //guide line
        commentService.save(commentService.createCommentOfReviewDto(userId , reviewId , content));
    }

    @PostMapping("register/review")
    public void commentOfReview(Long userId , Long reviewId, String content) {
        commentService.save(commentService.createCommentOfReviewDto(userId , reviewId , content));
    }

    @GetMapping("inquiry/{reviewId}/{userId}") //이거 하면 그냥 review에 달린 댓글들이 다 나옴
    public List<TransferComment> inquiryCommentOfReview(@PathVariable Long reviewId , @PathVariable Long userId){
        System.out.println("call the inquiryReview Method");
        return reviewService.getCommentOfReview(reviewId , userId);
    }

    @GetMapping("register/nested/{userId}/{commentId}/{content}") //자신의 댓글에다가 댓글을 다는 경우에는 자신의 테이블에 있는 레코드를 가르키게 됨 , 일단은 join 연산으로 구현할 생각은 못한 상태이다.
    public void getCommentOfComment(@PathVariable Long userId , @PathVariable Long commentId, @PathVariable String content) { //guide line
        commentService.save(commentService.createCommentOfCommentDto(userId ,commentId , content));
    }

    @PostMapping("register/nested")
    public void commentOfComment(Long userId , Long commentId, String content) {
        commentService.save(commentService.createCommentOfCommentDto(userId, commentId , content));
    }

    @GetMapping("delete/{reviewId}/{commentId}/{userId}")
    public MessageWithList getDelete(@PathVariable Long reviewId ,@PathVariable Long commentId ,@PathVariable Long userId){ //어떤 리뷰를 조회했을 때 나오는 댓글인지 , 그 정보를 알려주기 위해서 reviewId를 받아야함
        return commentService.deleteComment(reviewId , commentId , userId);
    }

    @DeleteMapping("delete")
    public MessageWithList delete(Long reviewId , Long commentId , Long userId){ //어떤 리뷰를 조회했을 때 나오는 댓글인지 , 그 정보를 알려주기 위해서 reviewId를 받아야함
        return commentService.deleteComment(reviewId , commentId , userId);
    }
}
