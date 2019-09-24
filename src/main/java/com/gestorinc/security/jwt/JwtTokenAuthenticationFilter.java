package com.gestorinc.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.exception.enums.Error;
import com.gestorinc.exception.jwt.InvalidJwtAuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.gestorinc.exception.enums.Error.HA_OCURRIDO_UN_ERROR_EN_EL_PROCESO_FAVOR_INTENTAR_MÁS_TARDE_COD_6;
import static com.gestorinc.utils.Constants.ER;
import static java.util.Optional.of;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

public class JwtTokenAuthenticationFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse =(HttpServletResponse) response;
        try {
            jwtTokenProvider.resolveToken((HttpServletRequest) request)
                    .ifPresent(this::authenticate);
            filterChain.doFilter(request, response);
        }catch (InvalidJwtAuthenticationException e) {
            httpServletResponse.setStatus(SC_UNAUTHORIZED);
            handleException(httpServletResponse, Error.ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8);
        }
        catch (RuntimeException e) {
            httpServletResponse.setStatus(SC_INTERNAL_SERVER_ERROR);
            handleException(httpServletResponse, HA_OCURRIDO_UN_ERROR_EN_EL_PROCESO_FAVOR_INTENTAR_MÁS_TARDE_COD_6);
        }
    }

    private void handleException(HttpServletResponse response, Error error) throws IOException {
        response.getWriter().write(
                new ObjectMapper()
                        .writeValueAsString(
                                new ErrorRestControllerResponse(ER, error.getCode())));
    }

    private void authenticate(String token) {

        if (jwtTokenProvider.validateToken(token)) {
            of(jwtTokenProvider.getAuthentication(token))
                    .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
        }
    }
}