package me.sungwon.jwt_tutorial.config;

import me.sungwon.jwt_tutorial.jwt.JwtAccessDenyHandler;
import me.sungwon.jwt_tutorial.jwt.JwtAuthenticationEntryPoint;
import me.sungwon.jwt_tutorial.jwt.JwtSecurityConfig;
import me.sungwon.jwt_tutorial.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.FilterChain;

@EnableWebSecurity // 기본적인 웹 보안을 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDenyHandler jwtAccessDenyHandler;

    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDenyHandler jwtAccessDenyHandler
    ){
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDenyHandler = jwtAccessDenyHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain httpConfigure(HttpSecurity http) throws Exception{
        return http
                .csrf().disable()// token 방식이기 때문

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDenyHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session 을 사용하지 않고 token 방식을 사용 할 것임

                .and()
                .authorizeRequests() // HttpServletRequest 를 사용하는 요청들에 대한 접근제한 설정
                .antMatchers("/api/hello").permitAll() // token 이 없는 상태의 접근
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/signup").permitAll()

                .anyRequest().authenticated()// antMatcher() 의 Argument 를 제외한 모든 uri 경로에 인증을 포함

                .and()
                .apply(new JwtSecurityConfig(tokenProvider)) // addFilterBefore 로 등록

                .and().build();
    }

    @Bean
    public WebSecurityCustomizer webConfigure(){
        return (web)->web.ignoring().antMatchers("/h2-console/**", "/favicon.ico", "/error");
    }
}