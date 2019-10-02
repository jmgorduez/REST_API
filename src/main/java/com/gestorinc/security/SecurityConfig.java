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

import static com.gestorinc.utils.Constants.AUTENTICAR;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
        return new String[]{ "/h2-console/**", "/favicon.ico"};
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
}