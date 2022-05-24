package com.example.lenpdok.controller;

import com.example.lenpdok.jwt.JwtFilter;
import com.example.lenpdok.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class MainController {

    private JwtFilter filter;
    @Autowired
    UserService userService;

    @GetMapping("/main")
    public String main(HttpServletResponse response, HttpServletRequest request) {
        return "home";
    }

    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "login/loginForm";
    }

}
