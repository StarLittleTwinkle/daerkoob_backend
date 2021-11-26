package com.project.daerkoob.model;

import com.project.daerkoob.domain.Friend;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class MessageWithFriendList {
    boolean flag;
    String message;
    List<Friend> friendList;
}
