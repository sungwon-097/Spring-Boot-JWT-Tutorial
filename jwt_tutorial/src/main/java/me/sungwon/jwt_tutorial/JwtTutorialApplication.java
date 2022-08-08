package me.sungwon.jwt_tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class JwtTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtTutorialApplication.class, args);
    }

}
