package com.project.daerkoob.model;

import com.project.daerkoob.domain.Comment;
import com.project.daerkoob.domain.Review;
import com.project.daerkoob.domain.User;
import lombok.Data;
import java.util.List;

@Data
public class TransferComment {
    Long id;
    String content;
    Review review;
    User writer;
    Long thumbCount;
    List<Comment> comments;
}
