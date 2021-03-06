package com.example.lenpdok.service;

import com.example.lenpdok.mapper.UserRepository;
import com.example.lenpdok.model.Authority;
import com.example.lenpdok.model.User;
import com.example.lenpdok.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // username이 DB에 존재하지 않으면 Authority와 User 정보를 생성해서 UserRepository의 save메소드를 통해 DB에 정보를 저장합니다.
    @Transactional
    public UserDto signup(UserDto userDto) {
        //유저검증
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        // 유저를 dto로 만들어 반환
        return UserDto.from(userRepository.save(user));
    }

//    @Transactional(readOnly = true)
//    public UserDto getUserWithAuthorities(String username) {
//        return UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
//    }
//
//    @Transactional(readOnly = true)
//    public UserDto getMyUserWithAuthorities() {
//        return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null));
//    }

}