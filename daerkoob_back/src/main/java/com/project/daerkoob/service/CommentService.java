package com.project.daerkoob.service;

import com.project.daerkoob.domain.Comment;
import com.project.daerkoob.repository.CommentRepository;
import com.project.daerkoob.repository.ReviewRepository;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;

    public CommentService(CommentRepository commentRepository , ReviewRepository reviewRepository , UserRepository userRepository){
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
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
