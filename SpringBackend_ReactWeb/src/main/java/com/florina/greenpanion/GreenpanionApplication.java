package com.florina.greenpanion;

//import com.florina.greenpanion.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class GreenpanionApplication {
    @Autowired

    public static void main(String[] args) {
        SpringApplication.run(GreenpanionApplication.class, args);
    }

}
