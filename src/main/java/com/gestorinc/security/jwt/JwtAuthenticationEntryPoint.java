package com.gestorinc.security.jwt;

import com.gestorinc.controller.abstracts.IInteractionLogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.gestorinc.exception.enums.Error.ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8;
import static com.gestorinc.utils.Constants.*;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private IInteractionLogManager logManager;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
;
        logManager.generateAuditLogError(ERROR_8_RESPONSE,
                ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, JWT_AUTHENTICATION_FAILED);
        response.getWriter()
                .write(OBJECT_MAPPER
                        .writeValueAsString(ERROR_8_RESPONSE));
    }
}