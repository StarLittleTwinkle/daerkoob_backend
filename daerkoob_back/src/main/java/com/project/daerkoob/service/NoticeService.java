package com.project.daerkoob.service;

import com.project.daerkoob.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    private NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository){
        this.noticeRepository = noticeRepository;
    }
}
