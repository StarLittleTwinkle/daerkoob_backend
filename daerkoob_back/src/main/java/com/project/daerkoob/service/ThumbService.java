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

    public Message judgeReview(Long userIndex , Review review){
        Optional<Thumb> tempThumb = thumbRepository.findByUserIdAndBookIdAndReviewIdAndGivenUserId(review.getUserId() , review.getBookId() , review.getId() , userIndex);
        Optional<Review> tempReview = reviewRepository.findById(review.getId());

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
            thumbRepository.save(createReviewThumb(userIndex , review));
        }
        return new Message(true , "좋아요 생성");
    }

    public Message judgeTranscription(Long userIndex, Transcription transcription){
        Optional<Thumb> tempThumb = thumbRepository.findByUserIdAndBookIdAndReviewIdAndGivenUserId(transcription.getUserId() , transcription.getBookId() , transcription.getId() , userIndex);
        Optional<Transcription> tempTranscription = transcriptionRepository.findById(transcription.getId());

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
            thumbRepository.save(createTranscriptionThumb(userIndex , transcription));
        }
        return new Message(true , "좋아요 생성");
    }

    public Thumb createReviewThumb(Long userIndex , Review review){
        Thumb thumb = new Thumb();
        thumb.setBookId(review.getBookId());
        thumb.setUserId(review.getUserId());
        thumb.setReviewId(review.getId());
        thumb.setGivenUserId(userIndex);
        return thumb;
    }

    public Thumb createTranscriptionThumb(Long userIndex , Transcription transcription){
        Thumb thumb = new Thumb();
        thumb.setBookId(transcription.getBookId());
        thumb.setUserId(transcription.getUserId());
        thumb.setReviewId(transcription.getId());
        thumb.setGivenUserId(userIndex);
        return thumb;
    }
}
