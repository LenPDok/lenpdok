package com.example.lenpdok.controller;

import com.example.lenpdok.service.StudyService;
import com.example.lenpdok.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    StudyService studyService;

    @GetMapping(value = {"/", "/main"})
    public String main(HttpServletResponse response, HttpServletRequest request, Model model) {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login/loginForm";
    }

}
