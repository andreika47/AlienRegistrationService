package com.andreika47.ctf.alienreg;

import com.andreika47.ctf.alienreg.model.Registration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

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