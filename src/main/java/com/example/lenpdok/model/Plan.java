package com.example.lenpdok.model;

import lombok.Data;
import java.util.Date;

/**
 * Plan DTO
 */
@Data
public class Plan {
    private int id;
    private String login_id;
    private String subject;
    private String title;
    private Date plan_date;
    private boolean activate;
}