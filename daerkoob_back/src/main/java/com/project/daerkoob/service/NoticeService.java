package com.project.daerkoob.service;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Notice;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Notice save(Notice notice){
        return noticeRepository.save(createDto(notice.getTitle() , notice.getContent()));
    }

    public Notice findById(Long id){
        return noticeRepository.findById(id).get();
    }

    public MessageWithList getNotice(Long pageNumber){
        Page<Notice> notices = noticeRepository.findAllByOrderByRegisterDateDesc(PageRequest.of(pageNumber.intValue() , 10));
        return new MessageWithList(new Long(notices.getTotalElements()) , new Message(true , "성공적으로 가져왔습니다.") , new ArrayList<>(notices.getContent()));
    }

    public MessageWithList setNotice(String title , String content){
        noticeRepository.save(createDto(title , content));
        return getNotice(0L);
    }

    public Notice createDto(String title , String content){
        Notice notice =  new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setRegisterDate(LocalDateTime.now());
        return notice;
    }

    public MessageWithList update(Long id , String title , String content){
        Notice notice = noticeRepository.findById(id).get();

        // title , content 중에 수정하지 않은 것들도 있을 수도 있고 , 입력하지 않고 넘길 수도 있는 예외 사항들을 처리함
        Notice modifyNotice = createDto(title == null ? notice.getTitle() : title ,
                content == null ? notice.getContent() : content);

        modifyNotice.setId(id);
        noticeRepository.save(modifyNotice);
        return getNotice(0L);
    }

    public MessageWithList delete(Long noticeId){
        noticeRepository.deleteById(noticeId);
        return getNotice(0L);
    }
}
