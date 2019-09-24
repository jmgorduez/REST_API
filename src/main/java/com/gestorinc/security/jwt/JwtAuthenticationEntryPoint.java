package com.gestorinc.security.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.gestorinc.controller.AbstractController.errorResponse;
import static com.gestorinc.exception.enums.Error.ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8;
import static com.gestorinc.utils.Constants.JWT_AUTHENTICATION_FAILED;
import static com.gestorinc.utils.Constants.OBJECT_MAPPER;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, JWT_AUTHENTICATION_FAILED);
        response.getWriter()
                .write(OBJECT_MAPPER
                        .writeValueAsString(
                                errorResponse(ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8)));
    }
}