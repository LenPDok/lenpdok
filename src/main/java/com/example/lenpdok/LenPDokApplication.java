package com.example.lenpdok;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"com.example.lenpdok.mapper"})
@SpringBootApplication
public class LenPDokApplication {

    public static void main(String[] args) {
        SpringApplication.run(LenPDokApplication.class, args);
    }

}
