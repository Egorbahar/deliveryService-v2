package com.exposit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.exposit.controller")
public class Starter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
