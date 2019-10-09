package com.gestorinc.security;

import com.gestorinc.security.jwt.JwtAuthenticationEntryPoint;
import com.gestorinc.security.jwt.JwtSecurityConfigurer;
import com.gestorinc.security.jwt.JwtTokenAuthenticationFilter;
import com.gestorinc.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.swagger.web.UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@EnableSwagger2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String COM_GESTORINC_CONTROLLER = "com.gestorinc.controller";
    public static final String DEFAULT_INCLUDE_PATTERN = "/v1/.*";
    public static final String HEADER = "header";
    public static final String LIST = "list";
    public static final String ALPHA = "alpha";
    public static final String SCHEMA = "schema";


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and().sessionManagement().sessionCreationPolicy(STATELESS)
                .and().authorizeRequests()
                .antMatchers(swaggerURLs()).permitAll()
                .antMatchers(h2ConsoleURLs()).permitAll()
                .antMatchers(AUTENTICAR).permitAll()
                .anyRequest().authenticated()
                .and().apply(new JwtSecurityConfigurer(jwtTokenAuthenticationFilter()))
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint());
    }

    private String[] h2ConsoleURLs() {
        return new String[]{"/h2-console/**", "/favicon.ico"};
    }

    private String[] swaggerURLs() {
        return new String[]{"/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"};
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter() {
        return new JwtTokenAuthenticationFilter(jwtTokenProvider);
    }

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