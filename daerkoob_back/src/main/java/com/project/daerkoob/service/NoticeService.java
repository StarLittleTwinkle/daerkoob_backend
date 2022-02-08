package com.project.daerkoob.service;

import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    private NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository){
        this.noticeRepository = noticeRepository;
    }

    public MessageWithList getNotice(){
        /*
        notice 그냥 register date 역순으로 가져오기
        여기에서도 pagination 적용?
         */
    }
}
