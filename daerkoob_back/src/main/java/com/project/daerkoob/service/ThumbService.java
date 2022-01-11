package com.project.daerkoob.service;

import com.project.daerkoob.domain.*;
import com.project.daerkoob.repository.CommentRepository;
import com.project.daerkoob.repository.ReviewRepository;
import com.project.daerkoob.repository.ThumbRepository;
import com.project.daerkoob.repository.TranscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThumbService {

    ThumbRepository thumbRepository;
    ReviewRepository reviewRepository;
    TranscriptionRepository transcriptionRepository;
    CommentRepository commentRepository;

    public ThumbService(ThumbRepository thumbRepository , ReviewRepository reviewRepository , TranscriptionRepository transcriptionRepository, CommentRepository commentRepository){
        this.thumbRepository = thumbRepository;
        this.reviewRepository = reviewRepository;
        this.transcriptionRepository = transcriptionRepository;
        this.commentRepository = commentRepository;
    }

    public Message judgeReview(Long userIndex , Long reviewId){
        Optional<Thumb> tempThumb = thumbRepository.findByReviewIdAndGivenUserId(reviewId , userIndex);
        Optional<Review> tempReview = reviewRepository.findById(reviewId);

        Thumb resultThumb = tempThumb.orElse(null);
        Review resultReview = tempReview.orElse(null);

        if (resultThumb != null){
            thumbRepository.deleteById(resultThumb.getId());
            resultReview.setThumbCount(resultReview.getThumbCount() - 1); //review 의 star , star_count 다시 update
            reviewRepository.save(resultReview);
            return new Message(true , "좋아요 제거");
        }
        else{
            resultReview.setThumbCount(resultReview.getThumbCount() + 1); //review 의 star , star_count 다시 update
            reviewRepository.save(resultReview);
            thumbRepository.save(createReviewThumb(userIndex , reviewId));
        }
        return new Message(true , "좋아요 생성");
    }

    public Message judgeTranscription(Long userIndex , Long transcriptionId){
        Optional<Thumb> tempThumb = thumbRepository.findByTranscriptionIdAndGivenUserId(transcriptionId , userIndex);
        Optional<Transcription> tempTranscription = transcriptionRepository.findById(transcriptionId);

        Thumb resultThumb = tempThumb.orElse(null);
        Transcription resultTranscription = tempTranscription.orElse(null);

        if (resultThumb != null){
            thumbRepository.deleteById(resultThumb.getId());
            resultTranscription.setThumbCount(resultTranscription.getThumbCount() - 1); //review 의 star , star_count 다시 update
            transcriptionRepository.save(resultTranscription);
            return new Message(true , "좋아요 제거");
        }
        else{
            resultTranscription.setThumbCount(resultTranscription.getThumbCount() + 1); //review 의 star , star_count 다시 update
            transcriptionRepository.save(resultTranscription);
            thumbRepository.save(createTranscriptionThumb(userIndex , transcriptionId));
        }
        return new Message(true , "좋아요 생성");
    }

    public Message judgeComment(Long userIndex , Long commentId){
        Optional<Thumb> tempThumb = thumbRepository.findByCommentIdAndGivenUserId(commentId , userIndex);
        Optional<Comment> tempComment = commentRepository.findById(commentId);

        Thumb resultThumb = tempThumb.orElse(null);
        Comment resultComment = tempComment.orElse(null);

        if (resultThumb != null){
            thumbRepository.deleteById(resultThumb.getId());
            resultComment.setThumbCount(resultComment.getThumbCount() - 1); //review 의 star , star_count 다시 update
            commentRepository.save(resultComment);
            return new Message(true , "좋아요 제거");
        }
        else{
            resultComment.setThumbCount(resultComment.getThumbCount() + 1); //review 의 star , star_count 다시 update
            commentRepository.save(resultComment);
            thumbRepository.save(createCommentThumb(userIndex , commentId));
        }
        return new Message(true , "좋아요 생성");
    }

    public Thumb createReviewThumb(Long userIndex, Long reviewId){
        Thumb thumb = new Thumb();
        thumb.setReviewId(reviewId);
        thumb.setGivenUserId(userIndex);
        return thumb;
    }

    public Thumb createTranscriptionThumb(Long userIndex, Long transcriptionId){
        Thumb thumb = new Thumb();
        thumb.setTranscriptionId(transcriptionId);
        thumb.setGivenUserId(userIndex);
        return thumb;
    }

    public Thumb createCommentThumb(Long userIndex, Long commentId){
        Thumb thumb = new Thumb();
        thumb.setCommentId(commentId);
        thumb.setGivenUserId(userIndex);
        return thumb;
    }
}
