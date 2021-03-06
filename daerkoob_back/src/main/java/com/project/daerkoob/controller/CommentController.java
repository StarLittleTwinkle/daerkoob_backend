package com.project.daerkoob.controller;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.model.Pagination;
import com.project.daerkoob.service.CommentService;
import com.project.daerkoob.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;
    private final ReviewService reviewService;

    @PostMapping("register/review")
    public Message commentOfReview(Long userId , Long reviewId, String content) { // content 그냥 공백이면 안받도록 수정
        if(!content.isBlank()) {
            commentService.save(commentService.createCommentOfReviewDto(userId, reviewId, content));
            return new Message(true, "입력에 성공하셨습니다.");}
        else
            return new Message(false, "다시 입력해주세요.");
    }

    @GetMapping("inquiry/{reviewId}/{userId}/{pageNumber}") //이거 하면 그냥 review에 달린 댓글들이 다 나옴
    public MessageWithList inquiryCommentOfReview(@PathVariable Long reviewId , @PathVariable Long userId , @PathVariable Long pageNumber){
        //MessageWithList는 그대로 유지하면서 그냥 commentService로 getCommentOfReview를 부르고 getCommentOfReview가 ReviewService의 getCommentOfReview의 List<TransferComment> 를 받아오는 형식으로 작성하였음
        return commentService.getCommentOfReview(reviewId , userId , pageNumber);
    }

    @PostMapping("register/nested")
    public Message commentOfComment(Long userId , Long commentId, String content) {
        if(!content.isBlank()) {
            commentService.save(commentService.createCommentOfCommentDto(userId, commentId, content));
            return new Message(true, "입력에 성공하셨습니다.");}
        else
            return new Message(false, "다시 입력해주세요.");
    }

    @PostMapping("delete")
    public MessageWithList delete(Long reviewId , Long commentId , Long userId){ //어떤 리뷰를 조회했을 때 나오는 댓글인지 , 그 정보를 알려주기 위해서 reviewId를 받아야함
        return commentService.deleteComment(reviewId , commentId , userId);
    }
}
