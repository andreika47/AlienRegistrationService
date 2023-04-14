package com.andreika47.ctf.alienreg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AlienRegApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AlienRegApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	return builder.sources(AlienRegApp.class);
    }
}