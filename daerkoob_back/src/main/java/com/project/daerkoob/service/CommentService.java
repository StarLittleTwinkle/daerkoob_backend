package com.project.daerkoob.service;

import com.project.daerkoob.domain.Comment;
import com.project.daerkoob.repository.CommentRepository;
import com.project.daerkoob.repository.ReviewRepository;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.stereotype.Service;

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
        comment.setNestedComment(commentId);
        comment.setThumbCount(0L);
        comment.setWriter(userRepository.findById(userId).get());
        comment.setContent(content);
        return comment;
    }
}
