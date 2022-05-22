package com.example.lenpdok.controller;

import com.example.lenpdok.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/main")
    public String main() {
        return "home";
    }

    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "login/loginForm";
    }

}
