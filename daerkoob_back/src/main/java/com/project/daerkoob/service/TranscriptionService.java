package com.project.daerkoob.service;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.model.CountWithList;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.model.TransferTranscription;
import com.project.daerkoob.repository.BookRepository;
import com.project.daerkoob.repository.ThumbRepository;
import com.project.daerkoob.repository.TranscriptionRepository;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.project.daerkoob.domain.Book;
import java.time.LocalDateTime;
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

    public MessageWithList transcriptionDelete(Long userId, Long transcriptionId){ //userId와 비교하면서 transcription이 지우기가 가능한지 판단하고
        //그리고 user의 transcriptionCount를 떨어트려야함
        Transcription transcription = transcriptionRepository.findById(transcriptionId).get();
        if(transcription.getUser().getId() != userId){
            return new MessageWithList(false, "필사 삭제에 실패했습니다." , new ArrayList<>(getBookTranscription(userId, transcription.getBook().getId())));
        }
        else{
            //여기서 일단 user transcriptionCount , bookTranscriptionCount
            transcriptionRepository.deleteById(transcriptionId);
            Book book = bookRepository.findById(transcription.getBook().getId()).get();
            User user = userRepository.findById(userId).get();
            user.setTranscriptionCount(user.getTranscriptionCount() - 1);
            book.setTranscriptionCount(book.getTranscriptionCount() - 1);
            userRepository.save(user);
            bookRepository.save(book);
            return new MessageWithList(true, "필사 삭제에 성공했습니다." , new ArrayList<>(getBookTranscription(userId, transcription.getBook().getId())));
        }
    }
    public Long countAll(){
        return transcriptionRepository.count();
    }

    public List<TransferTranscription> getUserTranscription(Long userId){
        List<Transcription> transcriptions = transcriptionRepository.findByUser(userRepository.findById(userId).get()); //일단 해당 유저의 transcription 얻고
        List<TransferTranscription> transferTranscriptions = new ArrayList<>();
        for(Transcription transcription : transcriptions){
            transferTranscriptions.add(createTransferTranscription(userId, transcription));
        }
        return transferTranscriptions;
    }

    public List<Transcription> getTranscription(Long bookId){ //얘는 그냥 book에 review 달렸는지 안달렸는지 확인하는용
        List<Transcription> transcriptions = transcriptionRepository.findByBookId(bookId);
        return transcriptions;
    }

    public List<Transcription> getRecentTranscription(){
        return transcriptionRepository.findTop8ByOrderByRegisterDateDesc();

    }

    public CountWithList getBookTranscriptionOfCountWithList(Long userId , String isbn){
        Book book = bookRepository.findByIsbn(isbn).get();
        List<TransferTranscription> result = getBookTranscription(userId, book.getId());
        return new CountWithList(new Long(result.size()) , new ArrayList<>(result));
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
        LocalDateTime now = LocalDateTime.now();
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
