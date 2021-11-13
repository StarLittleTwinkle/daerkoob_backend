package com.project.daerkoob.controller;

import com.project.daerkoob.service.StarService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("star")
public class StarController {

    StarService starService;

    public StarController(StarService starService){
        this.starService = starService;
    }
}
