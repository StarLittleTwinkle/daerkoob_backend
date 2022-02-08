package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.model.Pagination;
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

    @PostMapping("register/review")
    public Message commentOfReview(Long userId , Long reviewId, String content) { // content 그냥 공백이면 안받도록 수정
        if(!content.isBlank()) {
            commentService.save(commentService.createCommentOfReviewDto(userId, reviewId, content));
            return new Message(true, "입력에 성공하셨습니다.");}
        else
            return new Message(false, "다시 입력해주세요.");
    }

    @GetMapping("inquiry/{reviewId}/{userId}") //이거 하면 그냥 review에 달린 댓글들이 다 나옴
    public MessageWithList inquiryCommentOfReview(@PathVariable Long reviewId , @PathVariable Long userId){
        //MessageWithList는 그대로 유지하면서 그냥 commentService로 getCommentOfReview를 부르고 getCommentOfReview가 ReviewService의 getCommentOfReview의 List<TransferComment> 를 받아오는 형식으로 작성하였음
        /*
        inquiry 에서 pagination 방법을 구현해보자.
        일단 getCommentOfReview 에서 가져와야 한다. 근데 여기서 pg , sz 정보를 받을 수가 있나? 전체 레코드 수를 모르는데?
        일단 page 정보대로 한번 해보자 pg를 1로 일단 받아서
         */
        return commentService.getCommentOfReview(reviewId , userId);
    }

    @PostMapping("register/nested")
    public Message commentOfComment(Long userId , Long commentId, String content) {
        if(!content.isBlank()) {
            commentService.save(commentService.createCommentOfCommentDto(userId, commentId, content));
            return new Message(true, "입력에 성공하셨습니다.");}
        else
            return new Message(false, "다시 입력해주세요.");
    }

    @DeleteMapping("delete")
    public MessageWithList delete(Long reviewId , Long commentId , Long userId){ //어떤 리뷰를 조회했을 때 나오는 댓글인지 , 그 정보를 알려주기 위해서 reviewId를 받아야함
        return commentService.deleteComment(reviewId , commentId , userId);
    }
}
