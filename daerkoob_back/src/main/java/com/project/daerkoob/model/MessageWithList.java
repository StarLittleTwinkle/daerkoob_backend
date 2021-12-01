package com.project.daerkoob.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class MessageWithList {
    boolean flag;
    String message;
    List<Object> List;
}
