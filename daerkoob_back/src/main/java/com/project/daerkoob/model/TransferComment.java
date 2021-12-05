package com.project.daerkoob.model;

import com.project.daerkoob.domain.Comment;
import com.project.daerkoob.domain.User;
import lombok.Data;
import java.util.List;

@Data
public class TransferComment {
    Long id;
    String content;
    User writer;
    Long thumbCount;
    Boolean thumbJudge;
    List<Comment> comments;
}
