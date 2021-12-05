package com.project.daerkoob.service;

import com.project.daerkoob.domain.Comment;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.repository.CommentRepository;
import com.project.daerkoob.repository.ReviewRepository;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private ReviewService reviewService;


    public CommentService(CommentRepository commentRepository , ReviewRepository reviewRepository , UserRepository userRepository , ReviewService reviewService){
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.reviewService = reviewService;
    }

    public MessageWithList deleteComment(Long reviewId , Long commentId , Long userId){
        //자 일단 지울 수 있는지 없는지 판단해서 보내야함
        //그거 하기 전에 그러면 해당 comment를 가져와야함
        Comment comment = commentRepository.findById(commentId).get();
        if(comment.getWriter().getId() != userId){
            return new MessageWithList(false , "삭제에 실패했습니다." , new ArrayList<Object>(reviewService.getCommentOfReview(reviewId , userId))); //어떠한 커멘트를 가져와야 할 까 ,
        }
        else{//일단 삭제되면 대댓글까지 다 삭제 되는 걸로
            commentRepository.deleteById(commentId);
            return new MessageWithList(true, "삭제에 성공했습니다." , new ArrayList<Object>(reviewService.getCommentOfReview(reviewId , userId)));
        }
    }

    public void save(Comment comment){
        commentRepository.save(comment);
    }

    public List<Comment> getCommentOfComment(Long commentId){
        return commentRepository.findById(commentId).get().getComments();
    }

    public Comment createCommentOfReviewDto(Long userId , Long reviewId, String content){
        Comment comment = new Comment();
        comment.setReview(reviewRepository.findById(reviewId).get());
        comment.setThumbCount(0L);
        comment.setWriter(userRepository.findById(userId).get());
        comment.setContent(content);
        return comment;
    }

    public Comment createCommentOfCommentDto(Long userId ,Long commentId , String content){
        Comment comment = new Comment();
        comment.setComment(commentRepository.findById(commentId).get());
        comment.setThumbCount(0L);
        comment.setWriter(userRepository.findById(userId).get());
        comment.setContent(content);
        return comment;
    }
}
