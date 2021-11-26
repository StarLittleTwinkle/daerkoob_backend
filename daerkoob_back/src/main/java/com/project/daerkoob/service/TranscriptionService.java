package com.project.daerkoob.service;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.model.TransferTranscription;
import com.project.daerkoob.repository.BookRepository;
import com.project.daerkoob.repository.ThumbRepository;
import com.project.daerkoob.repository.TranscriptionRepository;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TranscriptionService {

    private TranscriptionRepository transcriptionRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;
    private ThumbRepository thumbRepository;

    public TranscriptionService(TranscriptionRepository transcriptionRepository , UserRepository userRepository , BookRepository bookRepository, ThumbRepository thumbRepository){
        this.transcriptionRepository = transcriptionRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.thumbRepository = thumbRepository;
    }

    public Long countAll(){
        return transcriptionRepository.count();
    }
    public List<Transcription> getTranscription(Long bookId){
        List<Transcription> transcriptions = transcriptionRepository.findByBookId(bookId);
        return transcriptions;
    }
    public List<TransferTranscription> getBookTranscription(Long userId , Long bookId) {
        List<Transcription> transcriptions = transcriptionRepository.findByBookId(bookId);
        List<TransferTranscription> resultList = new ArrayList<>();
        for(Transcription transcription : transcriptions){
            resultList.add(createTransferTranscription(userId , transcription));
        }
        return resultList;
    } //내가 이 사람 필사에 좋아요를 눌렀는지 확인하는 방법..

    public TransferTranscription createTransferTranscription(Long userId , Transcription transcription){
        TransferTranscription transferTranscription = new TransferTranscription();
        transferTranscription.setBook(transcription.getBook());
        transferTranscription.setUser(transcription.getUser());
        transferTranscription.setId(transcription.getId());
        transferTranscription.setContent(transcription.getContent());
        transferTranscription.setThumbCount(transcription.getThumbCount());
        transferTranscription.setRegisterDate(transcription.getRegisterDate());
        transferTranscription.setThumbJudge(thumbRepository.existsByTranscriptionIdAndGivenUserId(transcription.getId() , userId));
        return transferTranscription;
    }

    public Transcription createDto(Long userId , Long bookId ,  String transcriptionContent){
        Transcription transcription = new Transcription();
        LocalDate now = LocalDate.now();
        transcription.setUser(userRepository.findById(userId).get());
        transcription.setBook(bookRepository.findById(bookId).get());
        transcription.setThumbCount(0L);
        transcription.setContent(transcriptionContent);
        transcription.setRegisterDate(now);
        return transcription;
    }

    public Message save(Transcription transcription){
        transcriptionRepository.save(transcription);
        Optional<User> findByUserId = userRepository.findById(transcription.getUser().getId());
        User resultUser = findByUserId.get();
        resultUser.setTranscriptionCount(resultUser.getTranscriptionCount() + 1);
        userRepository.save(resultUser);
        return new Message(true , "저장에 성공했습니다.");
    }

}
