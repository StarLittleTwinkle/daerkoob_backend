package com.project.daerkoob.service;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.repository.BookRepository;
import com.project.daerkoob.repository.TranscriptionRepository;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TranscriptionService {

    private TranscriptionRepository transcriptionRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    public TranscriptionService(TranscriptionRepository transcriptionRepository , UserRepository userRepository , BookRepository bookRepository){
        this.transcriptionRepository = transcriptionRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<Transcription> getTranscription(Long bookId) {
        List<Transcription> transcriptions = transcriptionRepository.findByBookId(bookId);
        return transcriptions;
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
