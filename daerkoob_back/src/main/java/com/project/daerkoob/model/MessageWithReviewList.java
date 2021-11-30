package com.project.daerkoob.model;

import com.project.daerkoob.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class MessageWithReviewList {
    boolean flag;
    String message;
    List<Review> reviewList;
}
