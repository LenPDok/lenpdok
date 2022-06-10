package com.example.lenpdok.controller;

import com.example.lenpdok.model.User;
import com.example.lenpdok.model.UserDto;
import com.example.lenpdok.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("/login/registration");
    }

    @PostMapping("/signup")
    public void signup(UserDto userDto, HttpServletResponse response) throws IOException {
        userService.signup(userDto);
        response.sendRedirect("/main");
    }
//
//    @GetMapping("/user")
//    //해당 메서드가 호출되기 이전에 권한을 검사한다. 현재 사용자의 권한이 파라미터의 권한 중 일치하는 것이 있는 경우 true 를 리턴
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity<UserDto> getMyUserInfo(HttpServletRequest request) {
//        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
//    }
//
//    @GetMapping("/user/{username}")
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username) {
//        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
//    }
}
