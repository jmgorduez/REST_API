package com.gestorinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
@ComponentScan( basePackages= {"com.gestorinc"})
@EnableSwagger2
public class SavingsFundContributionsApplication
		extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SavingsFundContributionsApplication.class, args);
    }

    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SavingsFundContributionsApplication.class);
    }
}
