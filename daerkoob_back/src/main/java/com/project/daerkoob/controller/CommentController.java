package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Comment;
import java.util.List;
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

    //일단 댓글 달기 , 삭제는 나중에 생각하자
    //두개를 생각 리뷰에 댓글을 다는 경우 , 댓글에 댓글을 다는 경우 일단은 그 2경우만 생각해보면
    @GetMapping("register/review/{userId}/{reviewId}/{content}") //review에다가 댓글을 달면 이 댓글은 , 저 리뷰를 가르키게 됨 , 근데 리뷰가 자신에게 달린 댓글을 셀 수 있으면 더 좋을 것 같은데.. 그거는 review 가 list의 사이즈를 체크 하면될 듯
    public void getCommentOfReview(@PathVariable Long userId , @PathVariable Long reviewId, @PathVariable String content) { //guide line
        commentService.save(commentService.createCommentOfReviewDto(userId , reviewId , content));
    }

    @PostMapping("register/review")
    public void commentOfReview(Long userId , Long reviewId, String content) {
        commentService.save(commentService.createCommentOfReviewDto(userId , reviewId , content));
    }

    @GetMapping("review/inquiry/{reviewId}")
    public List<Comment> inquiryCommentOfReview(@PathVariable Long reviewId){
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
