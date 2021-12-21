package com.project.daerkoob.service;

import com.project.daerkoob.domain.Friend;
import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Transcription;
import com.project.daerkoob.domain.User;
import com.project.daerkoob.model.TransferUser;
import com.project.daerkoob.repository.TranscriptionRepository;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private TranscriptionRepository transcriptionRepository;

    public UserService(UserRepository userRepository , TranscriptionRepository transcriptionRepository){
        this.userRepository = userRepository; //UserService가 userRepository 를 사용 가능하도록 dependency injection 을 추가
        this.transcriptionRepository = transcriptionRepository;
    }

    public Optional<User> findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    public Optional<User> findByNickName(String nickName){
        return userRepository.findByNickName(nickName);
    }

    public void save(User user){
        userRepository.save(createDto(user));
    }

    public User createDto(User user){
        user.setTranscriptionCount(0L);
        user.setReviewCount(0L);
        user.setFriendCount(0L);
        return user;
    }

    public TransferUser createTransferUser(User user){
        TransferUser transferUser = new TransferUser();
        transferUser.setId(user.getId());
        transferUser.setName(user.getName());
        transferUser.setNickName(user.getNickName());
        transferUser.setBirth(user.getBirth());
        transferUser.setFriendCount(user.getFriendCount());
        transferUser.setFriends(user.getFriends());
        transferUser.setReviewCount(user.getReviewCount());
        transferUser.setTranscriptionCount(user.getTranscriptionCount());
        return transferUser;
    }
    public Message signUp(User user , String confirmPassword){
        if (user.getUserId() == null || user.getUserId().length() == 0) {
            return new Message(false, "아이디를 입력하세요.");
        } else if (user.getName() == null || user.getName().length() == 0) {
            return new Message(false, "이름을 입력하세요.");
        } else if (user.getNickName() == null || user.getNickName().length() == 0) {
            return new Message(false, "닉네임을 입력하세요.");
        } else if (user.getPassword() == null || user.getPassword().length() == 0) {
            return new Message(false, "비밀번호를 입력하세요.");
        } else if (confirmPassword == null || confirmPassword.length() == 0) {
            return new Message(false, "비밀번호가 일치하지 않습니다.");
        } else if (user.getBirth() == null) {
            return new Message(false, "생일을 입력하세요.");
        } else if (user.getPassword().equals(confirmPassword)) {
            boolean existsByUserId = userRepository.existsByUserId(user.getUserId());
            boolean existsByNickName = userRepository.existsByNickName(user.getNickName());
            if(!existsByUserId && !existsByNickName) {
                userRepository.save(createDto(user));
                return new Message(true, "회원가입 성공");
            }
            else if(existsByUserId) {
                return new Message(false, "이미 존재하는 아이디입니다.");
            }
            else{
                return new Message(false , "이미 존재하는 닉네임입니다.");
            }
        }
        else{
            return new Message(false, "비밀번호를 다시 입력해주세요");
        }
    }

    public TransferUser login(User user){
        Optional<User> resultUser = userRepository.findByUserId(user.getUserId());
        User result = resultUser.orElse(null);
        if(result != null && result.getPassword().equals(user.getPassword())) {
            return createTransferUser(result);
        }
        return null;
    }

    public TransferUser findUser(Long id){
        return createTransferUser(userRepository.findById(id).get());
    }

    public int[] getUserRecordCount(Long userId , Long year){
        LocalDateTime startDate = LocalDateTime.of(year.intValue() , 1 , 1 , 0 , 0 , 0);
        LocalDateTime endDate = LocalDateTime.of(year.intValue() , 12 , 31 , 23 , 59 , 59);
        int[] record;
        int[] monthDay = new int[12];
        if(year%4 == 0 && year%100 !=0 || year%400 == 0){
            record = new int[366];
        }
        else {
            record = new int[365];
        }
        LocalDate newDate;
        for(int i = 1; i < 12; i++){ //1월 1일 = 0 1 월 31일 = 30 , 2월 1일? = 31 3월 1일 = 60
            newDate = LocalDate.of(year.intValue() , i , 1);
            monthDay[i] = monthDay[i - 1] + newDate.lengthOfMonth();
        }
        List<Transcription> dateList = transcriptionRepository.findByUserAndRegisterDateBetweenOrderByRegisterDateAsc(userRepository.findById(userId).get() , startDate , endDate); //일단 이렇게 날짜를 얻어오고
        for(Transcription transcription : dateList){
            record[monthDay[transcription.getRegisterDate().getMonthValue() - 1] + transcription.getRegisterDate().getDayOfMonth() - 1] += 1;
        }
        return record;
    }
}
