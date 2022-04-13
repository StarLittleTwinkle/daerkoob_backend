package com.project.daerkoob.service;

import com.project.daerkoob.domain.*;
import com.project.daerkoob.model.Grass;
import com.project.daerkoob.model.MessageWithGrass;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.model.TransferUser;
import com.project.daerkoob.repository.TranscriptionRepository;
import com.project.daerkoob.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TranscriptionRepository transcriptionRepository;

    public Optional<User> findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    // userId 를 받으면 , TransferUser 를 반환
    public TransferUser findById(Long id){
        return createTransferUser(userRepository.findById(id).get());
    }

    // 해당 유저를 찾아주는 method
    public MessageWithList findByNickName(Long userId, String nickName){
        User user = userRepository.findByNickName(nickName).orElse(null);
        Message message;

        // 없는 사람이면 message
        if(user == null){
            message = new Message(false , "해당 유저는 존재하지 않습니다.");
        }

        // 해당 유저가 본인이면 , message
        else if(userId == user.getId()){
            message = new Message(false , "본인을 검색할 수 없습니다.");
            user = null;
        }

        // 있는 경우면 그냥 넣어서 보내준다.
        else {
            message = new Message(true , "유저를 성공적으로 검색하였습니다.");
        }

        return new MessageWithList(user == null ? 0L : 1L , message , user == null ? null : new ArrayList<>(List.of(user)));
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

    public MessageWithGrass getUserRecordCount(Long userId , Long year){
        /*
        1. 내가 grass 보낼때 아얘 string 으로 이 배열은 bottom 이런식으로 보내자
        2. 그리고 총 getUserRecordCount할 때 몇개의 필사가 달려있었는지도 보내기
        3. 수정하자
         */
        LocalDateTime startDate = LocalDateTime.of(year.intValue() , 1 , 1 , 0 , 0 , 0);
        LocalDateTime endDate = LocalDateTime.of(year.intValue() , 12 , 31 , 23 , 59 , 59);
        MessageWithGrass transferGrass = new MessageWithGrass();
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
        transferGrass.setTotalSize(new Long(dateList.size()));
        for(Transcription transcription : dateList){
            record[monthDay[transcription.getRegisterDate().getMonthValue() - 1] + transcription.getRegisterDate().getDayOfMonth() - 1] += 1;
        }
        int monthIndex = 0;
        /*
        1. 일단 31까지는 계속 0으로 진행되다가
        2. 31이 딱 되었을 때에는 1로 진행되야함 그리고 항상 year , monthIndex + 1 , i + 1;
        3. result에다가 앞에다가 null을 추가해주고 시작할 것임 , 그 이유는 캘린더의 인덱스를 맞추기 위해서
        4. 그럴려면 일단 1월 1일이 무슨 요일인지 알아야한다. 숫자로 받으면 월 = 1 , .... 일 = 7임 getDayOfWeek().getValue() 하면
        5. 만일 이게 일욜이다 , 그러면 앞에 null이 추가 안되는 0 , 그리고 이게 토요일이다 -> 6개가 추가되어야 한다. getValue() % 7로 구하면 될 듯
         */
        int frontNullCount = LocalDate.of(year.intValue() , 1 ,1).getDayOfWeek().getValue() % 7;
        List<Grass> result = new ArrayList<>();
        for(int i = 0; i < frontNullCount; i++){
            result.add(new Grass(null , null , null));
        }
        for(int i = 0; i < record.length; i++){
            if(monthIndex != 11 && i >= monthDay[monthIndex + 1]){
                monthIndex++;
            }
            LocalDateTime innerDate = LocalDateTime.of(year.intValue() , monthIndex + 1 , i - monthDay[monthIndex] + 1 , 0 , 0 , 0);
            result.add(new Grass(innerDate , new Long(record[i])));
        }
        /*
        1. result 에 이제 각각 string 넣고 set으로 그러고서 transferGrass에다가 포함시키자.
        2. 1일 , 그리고 말일만 하면 된다.
        3. 그리고 앞에서 null을 추가한 만큼 더해서 result.get(index) 하면 된다. (frontNullCount를 더해서 추가하면 된다)
        4. 그럼 for문으로 1월부터 12월까지 돌면서 초일 , 끝일에 대해서 처리해줄 것임
        5. monthDay를 사용할 것임
        6. 안에서 처리는 일단 result.get(monthDay[i - 1] + frontNullCount)로 구하면 이게 1월 1일 결과임 이거에서 이제 direction을 조정해야함
        7. direction은 이 인덱스를 알아야함 monthDay[i - 1] + frontNullCount를 말이다. 그냥 이 이후로 초일이면 여기다가 추가로 6일 left , 말일이면 뒤로 right 넣어주면 된다 정확히 말일과 끝날만 다르게 하고
        8. 그래서 get해서 저장해놓고 안에 있는 Grass 객체 수정해서 다시 set으로 집어넣으면 될 듯
         */
        for(int i = 1; i <= 12; i++){
            int firstDayIndex= 0;
            int lastDayIndex = 0;
            if(i == 12){
                firstDayIndex = monthDay[i - 1] + frontNullCount;
                lastDayIndex = record.length + frontNullCount - 1;
            } else{
                firstDayIndex = monthDay[i - 1] + frontNullCount;
                lastDayIndex = monthDay[i] + frontNullCount - 1;
            }
            Grass firstDay = result.get(firstDayIndex);
            firstDay.setDirection("left top");
            Grass lastDay = result.get(lastDayIndex);
            lastDay.setDirection("right bottom");
            result.set(firstDayIndex , new Grass(firstDay.getDate() , firstDay.getCommit() , firstDay.getDirection()));
            result.set(lastDayIndex , new Grass(lastDay.getDate() , lastDay.getCommit() , lastDay.getDirection()));
            String firstDayAfterString= "left";
            String lastDayBeforeString = "right";
            for(int j = firstDayIndex + 1; j <= firstDayIndex + 6; j++){
                Grass innerGrass = result.get(j);
                innerGrass.setDirection(firstDayAfterString);
                result.set(j , new Grass(innerGrass.getDate() , innerGrass.getCommit() , innerGrass.getDirection()));
            }
            for(int j = lastDayIndex - 1; j >= lastDayIndex - 6; j--){
                Grass innerGrass = result.get(j);
                innerGrass.setDirection(lastDayBeforeString);
                result.set(j , new Grass(innerGrass.getDate() , innerGrass.getCommit() , innerGrass.getDirection()));
            }
        }
        /*
        1. 마지막으로 endNullCount를 해서 12월 31일이 몇 요일인지 해서
        2. 뒤에다가도 null을 추가해서 보내면 된다.
         */
        int endNullCount = 7 - (LocalDate.of(year.intValue() , 12 , 31).getDayOfWeek().getValue() % 7 + 1);
        for(int i = 0; i < endNullCount; i++){
            result.add(new Grass(null , null , null));
        }
        transferGrass.setGrass(result);
        return transferGrass;
    }

}
