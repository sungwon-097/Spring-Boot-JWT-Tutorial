package me.sungwon.jwt_tutorial.service;

import me.sungwon.jwt_tutorial.dto.UserDto;
import me.sungwon.jwt_tutorial.entity.Authority;
import me.sungwon.jwt_tutorial.entity.User;
import me.sungwon.jwt_tutorial.repository.UserRepository;
import me.sungwon.jwt_tutorial.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signup(UserDto userDto){ // 회원가입 로직
        if(userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null){
            throw new RuntimeException("Already sign up");
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

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username){ // username 기준 정보를 가져옴
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities(){ // Security Context 에 저장된 username 정보를 가져옴
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}