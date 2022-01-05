package com.project.daerkoob.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Grass {
    LocalDateTime date;
    Long commit;
    String direction;

    public Grass(LocalDateTime date , Long commit , String direction){
        this.date = date;
        this.commit = commit;
        this.direction = direction;
    }

    public Grass(LocalDateTime date , Long commit){
        this.date = date;
        this.commit = commit;
    }
}
