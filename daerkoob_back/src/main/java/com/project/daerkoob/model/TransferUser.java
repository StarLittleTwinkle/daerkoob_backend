package com.project.daerkoob.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.daerkoob.domain.Friend;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class TransferUser {
    private Long id;
    private String name;
    private String nickName;
    private Date birth;
    private Long friendCount;
    private List<Friend> friends;
    private Long reviewCount;
    private Long transcriptionCount;
}
