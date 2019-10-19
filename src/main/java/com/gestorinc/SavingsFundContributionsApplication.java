package com.gestorinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SavingsFundContributionsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SavingsFundContributionsApplication.class, args);
    }

    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SavingsFundContributionsApplication.class);
    }
}
