package com.project.daerkoob.model;

import lombok.Data;
import java.util.List;

@Data
public class MessageWithGrass {
    private Long totalSize;
    List<Grass> grass;
}
