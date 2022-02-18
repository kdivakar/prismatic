package com.dkothandan.prismatic;

import com.dkothandan.prismatic.utils.PrismaticConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(PrismaticConfig.class)
public class PrismaticApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrismaticApplication.class, args);
    }

}
