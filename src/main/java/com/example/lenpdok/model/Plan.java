package com.example.lenpdok.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "plan")
@Data
public class Plan {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "subject", length = 50)
    private String subject;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "plan_date")
    private Date plan_date;

    @Column(name = "activate")
    private boolean activate;
}