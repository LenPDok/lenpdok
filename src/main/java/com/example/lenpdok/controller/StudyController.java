package com.example.lenpdok.controller;

import com.example.lenpdok.model.Plan;
import com.example.lenpdok.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
public class StudyController {
    @Autowired
    StudyService studyService;

    @GetMapping("/write_plan")
    public ModelAndView plan() {
        ModelAndView modelAndView = new ModelAndView("/popup/08_2_popup");
        return modelAndView;
    }

    @GetMapping("/community")
    public ModelAndView community() {
        ModelAndView modelAndView = new ModelAndView("/community/list");
        return modelAndView;
    }
}
