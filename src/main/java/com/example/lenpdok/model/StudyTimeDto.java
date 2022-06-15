package com.example.lenpdok.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudyTimeDto {
    private int id;
    private String username;
    private int time;
    private String date;
}