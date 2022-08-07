package me.sungwon.jwt_tutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.FilterChain;

@EnableWebSecurity // 기본적인 웹 보안을 활성화
public class SecurityConfig{

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests() // HttpServletRequest 를 사용하는 요청들에 대한 접근제한 설정
                .antMatchers("/api/hello").permitAll()
                .anyRequest().authenticated()// antMatcher() 의 Argument 를 제외한 모든 uri 경로에 인증을 포함
                .and()
                .build();
    }
}
