package com.gestorinc.security;

import com.gestorinc.security.jwt.JwtAuthenticationEntryPoint;
import com.gestorinc.security.jwt.JwtSecurityConfigurer;
import com.gestorinc.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.gestorinc.utils.Constants.AUTENTICACION;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
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
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and().authorizeRequests()
                .antMatchers(AUTENTICACION).permitAll()
                .anyRequest().authenticated()
                .and().apply(new JwtSecurityConfigurer(jwtTokenProvider))
                .and().exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint());
    }
}