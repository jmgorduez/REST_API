package com.gestorinc.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static com.gestorinc.utils.Constants.*;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.swagger.web.UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String COM_GESTORINC_CONTROLLER = "com.gestorinc";
    public static final String DEFAULT_INCLUDE_PATTERN = "/v1/.*";
    public static final String HEADER = "header";
    public static final String LIST = "list";
    public static final String ALPHA = "alpha";
    public static final String SCHEMA = "schema";

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage(COM_GESTORINC_CONTROLLER))
                .build()
                .apiInfo(metaData())
                .tags(new Tag(PAGO_APORTES, ""),
                        new Tag(AUTENTICACION, ""))
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
        return new ApiKey(AUTHORIZATION, AUTHORIZATION, HEADER);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    @Bean
    public UiConfiguration uiConfig() {
        return new UiConfiguration(null, LIST,
                ALPHA, SCHEMA, DEFAULT_SUBMIT_METHODS, false, true, null);
    }
}