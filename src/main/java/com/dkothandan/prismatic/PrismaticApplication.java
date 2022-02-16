package com.dkothandan.prismatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PrismaticApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrismaticApplication.class, args);
    }

}
