package com.gestorinc.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.exception.enums.Error;
import com.gestorinc.exception.jwt.InvalidJwtAuthenticationException;
import com.gestorinc.security.CustomRequestWrapper;
import com.gestorinc.service.abstractions.IInteractionLogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.gestorinc.exception.enums.Error.ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8;
import static com.gestorinc.exception.enums.Error.HA_OCURRIDO_UN_ERROR_EN_EL_PROCESO_FAVOR_INTENTAR_MÁS_TARDE_COD_6;
import static com.gestorinc.utils.Constants.*;
import static java.util.Optional.of;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

public class JwtTokenAuthenticationFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private IInteractionLogManager logManager;

    public JwtTokenAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse =(HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        try {
            jwtTokenProvider.resolveToken((HttpServletRequest) request)
                    .ifPresent(this::authenticate);
            filterChain.doFilter(new CustomRequestWrapper(httpServletRequest), response);
        }catch (InvalidJwtAuthenticationException e) {
            httpServletResponse.setStatus(SC_UNAUTHORIZED);
            handleException(httpServletRequest, httpServletResponse, ERROR_8_RESPONSE,
                    ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8);
        }
        catch (RuntimeException e) {
            httpServletResponse.setStatus(SC_INTERNAL_SERVER_ERROR);
            handleException(httpServletRequest, httpServletResponse, ERROR_6_RESPONSE,
                    HA_OCURRIDO_UN_ERROR_EN_EL_PROCESO_FAVOR_INTENTAR_MÁS_TARDE_COD_6);
        }
    }

    private void handleException(HttpServletRequest httpServletRequest, HttpServletResponse response, ErrorRestControllerResponse errorResponse, Error error) throws IOException {

        logManager.generateAuditLogError(httpServletRequest, errorResponse, error.getMessage());
        response.setContentType(APPLICATION_JSON);
        response.getWriter().write(
                new ObjectMapper()
                        .writeValueAsString(errorResponse));
    }

    private void authenticate(String token) {

        if (jwtTokenProvider.validateToken(token)) {
            of(jwtTokenProvider.getAuthentication(token))
                    .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
        }
    }
}