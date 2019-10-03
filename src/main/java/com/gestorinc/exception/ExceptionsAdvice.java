package com.gestorinc.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.exception.enums.Error;
import com.gestorinc.exception.jwt.InvalidJwtAuthenticationException;
import com.gestorinc.service.abstractions.IInteractionLogManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.gestorinc.exception.enums.Error.*;
import static com.gestorinc.utils.Constants.EXCEPTION_;
import static com.gestorinc.utils.Constants.errorResponse;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class ExceptionsAdvice {

    @Autowired
    private IInteractionLogManager logManager;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @ExceptionHandler({LogicBusinessException.class})
    public ResponseEntity<ErrorRestControllerResponse> handleBusinessException(
            LogicBusinessException e)
            throws IOException {
        logManager.generateAuditLogError(httpServletRequest, errorResponse(e.getError()),e.getError().getMessage());
        return error(INTERNAL_SERVER_ERROR, e.getError(), e);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorRestControllerResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e)
            throws IOException {
        logManager.generateAuditLogError(httpServletRequest, errorResponse(ERROR_EN_LOS_PARAMETROS_RECIBIDOS_COD_10),
                ERROR_EN_LOS_PARAMETROS_RECIBIDOS_COD_10.getMessage());
        return error(BAD_REQUEST,
                ERROR_EN_LOS_PARAMETROS_RECIBIDOS_COD_10, e);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErrorRestControllerResponse> handleBadCredentialsException(
            BadCredentialsException e)
            throws IOException {
        logManager.generateAuditLogError(httpServletRequest, errorResponse(ERROR_DE_AUTENTICACIÓN_DE_BANCO_CREDENCIALES_NO_VALIDAS_COD_7),
                ERROR_DE_AUTENTICACIÓN_DE_BANCO_CREDENCIALES_NO_VALIDAS_COD_7.getMessage());
        return error(UNAUTHORIZED,
                ERROR_DE_AUTENTICACIÓN_DE_BANCO_CREDENCIALES_NO_VALIDAS_COD_7, e);
    }

    @ExceptionHandler({InvalidJwtAuthenticationException.class})
    public ResponseEntity<ErrorRestControllerResponse> handleInvalidJwtAuthenticationException(
            InvalidJwtAuthenticationException e) throws IOException {
        logManager.generateAuditLogError(httpServletRequest, errorResponse(ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8),
                ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8.getMessage());
        return error(UNAUTHORIZED,
                ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8, e);
    }

    @ExceptionHandler({RuntimeException.class, JsonProcessingException.class, IOException.class,
            HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<ErrorRestControllerResponse> handleRunTimeException(
            RuntimeException e) throws IOException {
        logManager.generateAuditLogError(httpServletRequest, errorResponse(HA_OCURRIDO_UN_ERROR_EN_EL_PROCESO_FAVOR_INTENTAR_MÁS_TARDE_COD_6),
                HA_OCURRIDO_UN_ERROR_EN_EL_PROCESO_FAVOR_INTENTAR_MÁS_TARDE_COD_6.getMessage());
        return error(INTERNAL_SERVER_ERROR,
                HA_OCURRIDO_UN_ERROR_EN_EL_PROCESO_FAVOR_INTENTAR_MÁS_TARDE_COD_6, e);
    }

    private ResponseEntity<ErrorRestControllerResponse> error(HttpStatus status, Error error, Exception e) {
        log.error(EXCEPTION_, e);
        return ResponseEntity.status(status).body(errorResponse(error));
    }
}