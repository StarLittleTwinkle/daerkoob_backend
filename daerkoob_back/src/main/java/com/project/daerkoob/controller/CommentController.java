package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.model.MessageWithList;
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
        근데 근본적인 해결 방안을 찾아야한다...
        delete api 에서는 개수를 반환하지 않는다 근데 이것은 문제가 있다는 것... 여기서도 분명히 해야함 , 근데 Message가 포함이 되어있는 것일 뿐, 여기서 CountWithList를 그냥 MessageWithList로 바꾸는 것은 어떨까?
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
