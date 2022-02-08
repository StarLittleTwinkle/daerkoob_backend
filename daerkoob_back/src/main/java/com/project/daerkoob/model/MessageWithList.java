package com.project.daerkoob.model;

import com.project.daerkoob.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class MessageWithList{
    //이런식으로 그냥 Message와 , CountWithList를 통합시킨다.
    private Long totalSize;
    private Message message;
    private List<Object> List;
}
