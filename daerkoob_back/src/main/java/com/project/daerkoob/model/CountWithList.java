package com.project.daerkoob.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class CountWithList {
    public Long totalCount;
    public List<Object> list;
}
