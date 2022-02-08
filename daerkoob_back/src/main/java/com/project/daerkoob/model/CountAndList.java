package com.project.daerkoob.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
@Data
@AllArgsConstructor
public class CountAndList {
    private Long totalCount;
    private List<Object> list;
}
