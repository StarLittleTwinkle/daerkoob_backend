package com.project.daerkoob.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
@Data
@AllArgsConstructor
public class CountAndList {
    private Long totalCount;
    private Long realCount;
    private List<Object> list;

    public CountAndList(Long totalCount , List<Object> list){
        this.totalCount = totalCount;
        this.list = list;
    }
}
