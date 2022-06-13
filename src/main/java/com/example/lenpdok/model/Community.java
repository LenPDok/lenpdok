package com.example.lenpdok.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "community")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Community {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "content", length = 1000)
    private String content;

    @Column(name = "write_date")
    private Date plan_date;
}