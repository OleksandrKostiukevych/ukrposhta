package com.ukrposhta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class UkrposhtaApplication {
    public static void main(String[] args) {
        SpringApplication.run(UkrposhtaApplication.class, args);
    }
}
