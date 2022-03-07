package com.project.daerkoob.service;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Notice;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.model.Pagination;
import com.project.daerkoob.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Notice save(Notice notice){
        notice = createDto(notice.getTitle() , notice.getContent());
        return noticeRepository.save(notice);
    }

    public MessageWithList getNotice(Long pageNumber){
        /*
        notice 그냥 register date 역순으로 가져오기
        여기에서도 pagination 적용?
         */
        System.out.println(pageNumber);
        Pagination pagination = new Pagination();
        pagination.setPageNumber(pageNumber.intValue());
        List<Notice> notices = noticeRepository.findAllByOrderByRegisterDateDesc(pagination);
        return new MessageWithList(new Long(pagination.getTotalRecordCount()) , new Message(true , "성공적으로 가져왔습니다.") , new ArrayList<>(notices));
    }

//    public Message setNotice(Long userIndex , String title , String content){
//
//        /*
//        11 = kpeel5839 , 16 = jiyeong star
//        이거 아니면 바로 차단 , 맞으면 등록
//        교수님 말대로 isBlank() 가 null은 처리를 못하나보다. Optional 로 감싸야하나 ? 근데 짜피 content 가 null로 들어올 일은 없으니까 그대로 가자
//         */
//        if((userIndex != 11 && userIndex != 16) || content.isBlank() || title.isBlank()) return new Message(false , "다시 작성하세요");
//        noticeRepository.save(createDto(title , content));
//        return new Message(true , "성공적으로 저장했습니다.");
//    }

    public Notice createDto(String title , String content){
        Notice notice =  new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setRegisterDate(LocalDateTime.now());
        return notice;
    }

    public Notice update(Long id , Notice notice){
        notice = createDto(notice.getTitle(), notice.getContent());
        notice.setId(id);
        return noticeRepository.save(notice);
    }

    public void delete(Long noticeId){
        noticeRepository.delete(noticeRepository.findById(noticeId).orElseThrow(EntityNotFoundException::new));
    }
}
