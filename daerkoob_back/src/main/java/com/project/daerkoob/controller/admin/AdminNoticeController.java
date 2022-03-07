package com.project.daerkoob.controller.admin;

import com.project.daerkoob.domain.Notice;
import com.project.daerkoob.repository.NoticeRepository;
import com.project.daerkoob.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/notices")
public class AdminNoticeController {

    private final NoticeService noticeService;
    private final NoticeRepository noticeRepository;

    public void noticeListMvcAddObject(ModelAndView mvc) {
        mvc.addObject("noticeList", noticeRepository.findAllByOrderByRegisterDateDesc());
    }

    public void noticeMvcAddObject(ModelAndView mvc){
        mvc.addObject("notice" , new Notice());
    }
    @GetMapping() // 처음 화면
    public ModelAndView setNotice() {
        ModelAndView mvc = new ModelAndView("notice/createNoticeForm");
        noticeMvcAddObject(mvc);
        noticeListMvcAddObject(mvc);
        return mvc;
    }

    @PostMapping(params = "cmd=register") // 등록
    public ModelAndView registerNotice(Notice notice) {
        System.out.println("register");
        ModelAndView mvc = new ModelAndView("notice/createNoticeForm");
        noticeService.save(notice);
        noticeMvcAddObject(mvc);
        noticeListMvcAddObject(mvc);
        return mvc; //등록화면으로 다시 넘어감
    }

    @PostMapping(params = "cmd=inquiry") // 조회
    public ModelAndView inquiryNotice(@RequestParam("id") Long noticeId) {
        System.out.println("inquiry");
        System.out.println(noticeRepository.findById(noticeId).get());
        ModelAndView mvc = new ModelAndView("notice/createNoticeForm");
        if (!noticeRepository.existsById(noticeId)) noticeMvcAddObject(mvc); // 없으면 빈 객체로
        else mvc.addObject("notice", noticeRepository.findById(noticeId).get()); // 있으면 채운채로
        noticeListMvcAddObject(mvc);
        return mvc;
    }

    @PostMapping(params = "cmd=update") // 업데이트
    public ModelAndView updateHof(@RequestParam("id") Long noticeId, Notice notice) {
        System.out.println("update");
        ModelAndView mvc = new ModelAndView("notice/createNoticeForm");
        noticeService.update(noticeId , notice); // update
        noticeMvcAddObject(mvc);
        noticeListMvcAddObject(mvc);
        return mvc; // 다시 편집 가능하도록
    }


    @PostMapping(params = "cmd=delete") // 삭제
    public ModelAndView deleteHof(@RequestParam("id") Long noticeId) {
        System.out.println("delete");
        ModelAndView mvc = new ModelAndView("notice/createNoticeForm");
        noticeService.delete(noticeId);
        noticeMvcAddObject(mvc);
        noticeListMvcAddObject(mvc);
        return mvc;
    }
}
