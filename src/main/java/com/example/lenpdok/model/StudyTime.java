package com.example.lenpdok.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="study_time")
@Data
public class StudyTime {
    @Id
    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "time", length = 5)
    private int time;

    @Column(name = "date")
    private Date date;
}
