package com.tistory.hitomis.springboot_practice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootPractice1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPractice1Application.class, args);
    }

}
