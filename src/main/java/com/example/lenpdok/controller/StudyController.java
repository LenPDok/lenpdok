package com.example.lenpdok.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
public class StudyController {
    @GetMapping("/write_plan")
    public ModelAndView plan() {
        ModelAndView modelAndView = new ModelAndView("08_2_popup");

        return modelAndView;
    }
}
