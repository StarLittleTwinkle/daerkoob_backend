package com.project.daerkoob.service;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.Star;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.repository.ReviewRepository;
import com.project.daerkoob.repository.StarRepository;
import com.project.daerkoob.repository.TranscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.lang.Math;

@Service
public class StarService {

    StarRepository starRepository;
    ReviewRepository reviewRepository;
    TranscriptionRepository transcriptionRepository;

    public StarService(StarRepository starRepository, ReviewRepository reviewRepository , TranscriptionRepository transcriptionRepository){
        this.starRepository = starRepository;
        this.reviewRepository = reviewRepository;
        this.transcriptionRepository = transcriptionRepository;
    }

    public Message judgeReview(Long userIndex , Review review , Long score){ //일단 star 주면 review 에 대한 star_count 가 올라가고 review 에 대한 star가 측정이 되어야 함
        Optional<Star> tempStar = starRepository.findByUserIdAndBookIdAndReviewIdAndGivenUserId(review.getUserId() , review.getBookId() , review.getId() , userIndex);
        Optional<Review> tempReview = reviewRepository.findById(review.getId());

        Star resultStar = tempStar.orElse(null);
        Review resultReview = tempReview.orElse(null);

        if (resultStar != null){
            starRepository.deleteById(resultStar.getId());
            resultReview.setStar(scoreCalculate(resultReview.getStar() , resultReview.getStarCount() , (score) * -1 , resultReview.getStarCount() - 1));
            resultReview.setStarCount(resultReview.getStarCount() - 1); //review 의 star , star_count 다시 update
            reviewRepository.save(resultReview);
            return new Message(true , "별점 제거");
        }
        else{
            resultReview.setStar(scoreCalculate(resultReview.getStar() , resultReview.getStarCount() , (score) , resultReview.getStarCount() + 1));
            resultReview.setStarCount(resultReview.getStarCount() + 1); //review 의 star , star_count 다시 update
            reviewRepository.save(resultReview);
            starRepository.save(createReviewStar(userIndex , review , score));
        }
        return new Message(true , "별점 생성");
    }

    public Message judgeTranscription(Long userIndex , Transcription transcription , Long score){
        Optional<Star> tempStar = starRepository.findByUserIdAndBookIdAndTranscriptionIdAndGivenUserId(transcription.getUserId() , transcription.getBookId() , transcription.getId() , userIndex);
        Optional<Transcription> tempTranscription = transcriptionRepository.findById(transcription.getId());
        Star resultStar = tempStar.orElse(null);
        Transcription resultTranscription = tempTranscription.orElse(null); //얻음 ..
        if (resultStar != null){
            starRepository.deleteById(resultStar.getId());
            resultTranscription.setStar(scoreCalculate(resultTranscription.getStar() , resultTranscription.getStarCount() , (score) * -1 , resultTranscription.getStarCount() - 1));
            resultTranscription.setStarCount(resultTranscription.getStarCount() - 1); //transcription 의 star , star_count 다시 update
            transcriptionRepository.save(resultTranscription);
            return new Message(true , "별점 제거");
        }
        else{
            resultTranscription.setStar(scoreCalculate(resultTranscription.getStar() , resultTranscription.getStarCount() , (score) , resultTranscription.getStarCount() + 1));
            resultTranscription.setStarCount(resultTranscription.getStarCount() + 1); //transcription 의 star , star_count 다시 update
            transcriptionRepository.save(resultTranscription);
            starRepository.save(createTranscriptionStar(userIndex , transcription , score));
        }
        return new Message(true , "별점 생성");
    }

    public Star createReviewStar(Long userIndex , Review review , Long score){
        Star star = new Star();
        star.setBookId(review.getBookId());
        star.setUserId(review.getUserId());
        star.setReviewId(review.getId());
        star.setScore(score);
        star.setGivenUserId(userIndex);
        return star;
    }

    public Star createTranscriptionStar(Long userIndex , Transcription transcription , Long score){
        Star star = new Star();
        star.setBookId(transcription.getBookId());
        star.setUserId(transcription.getUserId());
        star.setTranscriptionId(transcription.getId());
        star.setScore(score);
        star.setGivenUserId(userIndex);
        return star;
    }

    public Double scoreCalculate(Double previousScore , Long previousCount , Long score , Long presentCount){ //별점 주기
        Double resultScore = (double)Math.round(((previousScore * previousCount) + score) / presentCount * 100) / 100;
        return resultScore;
    }
}
