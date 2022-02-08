package com.project.daerkoob.service;

import com.project.daerkoob.domain.Message;
import com.project.daerkoob.domain.Notice;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.model.Pagination;
import com.project.daerkoob.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NoticeService {
    private NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository){
        this.noticeRepository = noticeRepository;
    }

    public MessageWithList getNotice(Long pageNumber){
        /*
        notice 그냥 register date 역순으로 가져오기
        여기에서도 pagination 적용?
         */
        Pagination pagination = new Pagination();
        List<Notice> notices = noticeRepository.findAllByOrderByRegisterDateDesc(pagination);
        return new MessageWithList(new Long(pagination.getTotalRecordCount()) , new Message(true , "성공적으로 가져왔습니다.") , new ArrayList<>(notices));
    }
}
