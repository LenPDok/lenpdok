package com.example.lenpdok.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    // 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스.
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello"); //정상적인 요청이면 메소드의 파라미터 내용을 반환한다.
    }
}
