package com.project.daerkoob.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class MessageWithList {
    private boolean flag;
    private String message;
    private List<Object> List;
}
