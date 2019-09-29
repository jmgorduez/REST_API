package com.gestorinc.swagger;

import com.gestorinc.utils.Constants;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static com.gestorinc.utils.Constants.AUTHORIZATION;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String COM_GESTORINC_CONTROLLER = "com.gestorinc";
    public static final String DEFAULT_INCLUDE_PATTERN = "/v1/.*";

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(COM_GESTORINC_CONTROLLER))
                .build()
                .apiInfo(metaData())
                .tags(new Tag(Constants.PAGO_APORTES, ""),
                        new Tag(Constants.AUTENTICACION, ""))
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .useDefaultResponseMessages(false);
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Documentación Servicios bancarios")
                .description("\"API REST para registrar transacciones de aporte hacia Confia.\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                //.contact(new Contact("John Thompson", "https://springframework.guru/about/", "john@springfrmework.guru"))
                .build();
    }


    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION, AUTHORIZATION, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

}