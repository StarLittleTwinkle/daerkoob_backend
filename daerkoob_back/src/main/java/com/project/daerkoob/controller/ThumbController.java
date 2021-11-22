package com.project.daerkoob.controller;

import com.project.daerkoob.service.ThumbService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("thumb")
public class ThumbController {

    private ThumbService thumbService;

    public ThumbController(ThumbService thumbService){
        this.thumbService = thumbService;
    }
}
