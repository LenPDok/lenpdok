package com.example.lenpdok.controller;

import com.example.lenpdok.jwt.JwtFilter;
import com.example.lenpdok.jwt.TokenProvider;
import com.example.lenpdok.mapper.UserRepository;
import com.example.lenpdok.model.*;
import com.example.lenpdok.service.StudyService;
import com.example.lenpdok.util.SecurityUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final StudyService studyService;
    private final UserRepository userRepository;

    private SecurityUtil securityUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/getPlan")
    public List<Plan> getPlanList() {
        String username = UserDto.from(securityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null)).getUsername();
        List<Plan> planList = studyService.getPlanList(username);
        System.out.println(planList);
        return planList;
    }

    @ResponseBody
    @PostMapping("/currentUsername")
    public String currentUsername() {
        String username = UserDto.from(securityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null)).getUsername();
        System.out.println(username);
        return username;
    }

    @ResponseBody
    @PostMapping("/savePlan")
    public String savePlan(HttpServletRequest request) {
        Plan plan = new Plan();
        Enumeration e = request.getParameterNames();
        while ( e.hasMoreElements() ){
            String name = (String) e.nextElement();
            String[] values = request.getParameterValues(name);
            for (String value : values) {
                if(!value.equals(""))
                {
                    plan.setTitle(value);
                    studyService.addPlan(plan);
                }
            }
        }
        String resultmsg="<script>alert('저장되었습니다.');self.close();opener.location.reload();</script>";
        return resultmsg;
    }
}