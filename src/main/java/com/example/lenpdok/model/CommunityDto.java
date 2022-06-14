package com.example.lenpdok.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityDto {
    private int id;
    private String title;
    private String username;
    private String content;
    private String write_date;
}