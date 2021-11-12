package com.project.daerkoob.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transcription")
public class Transcription {
}
