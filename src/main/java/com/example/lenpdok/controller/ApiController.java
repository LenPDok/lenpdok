package com.example.lenpdok.controller;

import com.example.lenpdok.jwt.JwtFilter;
import com.example.lenpdok.jwt.TokenProvider;
import com.example.lenpdok.mapper.UserRepository;
import com.example.lenpdok.model.*;
import com.example.lenpdok.service.CommunityService;
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
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final StudyService studyService;
    private final UserRepository userRepository;
    private final CommunityService communityService;

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
    @GetMapping("/getPlan")
    public List<Plan> getPlanList() {
        String username = UserDto.from(securityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null)).getUsername();
        List<Plan> planList = studyService.getPlanList(username);
        return planList;
    }

    @ResponseBody
    @GetMapping("/currentUsername")
    public String currentUsername() {
        String username = UserDto.from(securityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null)).getUsername();
        System.out.println("현재 접속 계정명 : " + username);
        return username;
    }

    @PostMapping("/savePlan")
    public String savePlan(@RequestBody HashMap<String, String> data) {
        String username = UserDto.from(securityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null)).getUsername();
        System.out.println("*********savePlan***********");
        System.out.println(data);
        Plan plan = new Plan();
        for(int i=1; i<data.size()+1; i++) {
            if(data.get(Integer.toString(i)) != null && data.get(Integer.toString(i)) != "") {
                plan.setTitle(data.get(Integer.toString(i)));
                studyService.addPlan(plan, username);
            }
        }
        String resultmsg="저장되었습니다.";
        return resultmsg;
    }

    @GetMapping("/getCommunity")
    public List<CommunityDto> getCommunityList() {
        List<CommunityDto> communityList = communityService.getCommunityList();
        return communityList;
    }

    @GetMapping("/getCommunity/{id}")
    public CommunityDto getCommunity(@PathVariable Integer id) {
        System.out.println(id);
        CommunityDto community = communityService.getCommunity(id);
        return community;
    }

    @PostMapping("/writeCommunity")
    public String writeCommunity(@RequestBody CommunityDto community) {
        community.setUsername(UserDto.from(securityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null)).getUsername());
        System.out.println(community);
        communityService.writeCommunity(community);
        return "저장되었습니다.";
    }

    @PutMapping("/updateCommunity")
    public String updateCommunity(@RequestBody CommunityDto community) {
        String currentUser = UserDto.from(securityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null)).getUsername();
        String communityUser = communityService.getCommunity(community.getId()).getUsername();
        if(currentUser.equals(communityUser)) {
            community.setUsername(currentUser);
            communityService.updateCommunity(community);
            return "수정되었습니다.";
        } else {
            return "권한이 없습니다.";
        }

    }

    @DeleteMapping("/deleteCommunity/{id}")
    public String deleteCommunity(@PathVariable Integer id) {
        String currentUser = UserDto.from(securityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null)).getUsername();
        String communityUser = communityService.getCommunity(id).getUsername();
        if(currentUser.equals(communityUser)) {
            communityService.deleteCommunity(id);
            return "삭제되었습니다.";
        } else {
            return "권한이 없습니다.";
        }


    }
}