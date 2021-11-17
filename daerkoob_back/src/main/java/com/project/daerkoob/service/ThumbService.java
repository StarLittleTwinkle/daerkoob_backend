package com.project.daerkoob.service;

import com.project.daerkoob.domain.*;
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

    public ThumbService(ThumbRepository thumbRepository , ReviewRepository reviewRepository , TranscriptionRepository transcriptionRepository){
        this.thumbRepository = thumbRepository;
        this.reviewRepository = reviewRepository;
        this.transcriptionRepository = transcriptionRepository;
    }

    public Message judgeReview(Long userIndex , Long userId , Long bookId , Long reviewId){
        Optional<Thumb> tempThumb = thumbRepository.findByUserIdAndBookIdAndReviewIdAndGivenUserId(userId , bookId , reviewId , userIndex);
        Optional<Review> tempReview = reviewRepository.findById(reviewId);

        Thumb resultThumb = tempThumb.orElse(null);
        Review resultReview = tempReview.orElse(null);

        if (resultThumb != null){
            thumbRepository.deleteById(resultThumb.getId());
            resultReview.setThumb(resultReview.getThumb() - 1); //review 의 star , star_count 다시 update
            reviewRepository.save(resultReview);
            return new Message(true , "좋아요 제거");
        }
        else{
            resultReview.setThumb(resultReview.getThumb() + 1); //review 의 star , star_count 다시 update
            reviewRepository.save(resultReview);
            thumbRepository.save(createReviewThumb(userIndex , userId, bookId , reviewId));
        }
        return new Message(true , "좋아요 생성");
    }

    public Message judgeTranscription(Long userIndex , Long userId , Long bookId , Long transcriptionId){
        Optional<Thumb> tempThumb = thumbRepository.findByUserIdAndBookIdAndTranscriptionIdAndGivenUserId(userId , bookId , transcriptionId , userIndex);
        Optional<Transcription> tempTranscription = transcriptionRepository.findById(transcriptionId);

        Thumb resultThumb = tempThumb.orElse(null);
        Transcription resultTranscription = tempTranscription.orElse(null);

        if (resultThumb != null){
            thumbRepository.deleteById(resultThumb.getId());
            resultTranscription.setThumb(resultTranscription.getThumb() - 1); //review 의 star , star_count 다시 update
            transcriptionRepository.save(resultTranscription);
            return new Message(true , "좋아요 제거");
        }
        else{
            resultTranscription.setThumb(resultTranscription.getThumb() + 1); //review 의 star , star_count 다시 update
            transcriptionRepository.save(resultTranscription);
            thumbRepository.save(createTranscriptionThumb(userIndex , userId ,bookId , transcriptionId));
        }
        return new Message(true , "좋아요 생성");
    }

    public Thumb createReviewThumb(Long userIndex , Long userId, Long bookId , Long reviewId){
        Thumb thumb = new Thumb();
        thumb.setBookId(bookId);
        thumb.setUserId(userId);
        thumb.setReviewId(reviewId);
        thumb.setGivenUserId(userIndex);
        return thumb;
    }

    public Thumb createTranscriptionThumb(Long userIndex , Long userId, Long bookId , Long transcriptionId){
        Thumb thumb = new Thumb();
        thumb.setBookId(bookId);
        thumb.setUserId(userId);
        thumb.setTranscriptionId(transcriptionId);
        thumb.setGivenUserId(userIndex);
        return thumb;
    }
}
