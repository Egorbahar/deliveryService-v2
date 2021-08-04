package com.exposit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.exposit.app")

public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
