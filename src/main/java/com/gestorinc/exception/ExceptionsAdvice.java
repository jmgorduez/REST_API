package com.gestorinc.exception;

import com.gestorinc.controller.model.AbstractResponse;
import com.gestorinc.controller.model.ErrorResponse;
import com.gestorinc.exception.enums.Error;
import com.gestorinc.exception.jwt.InvalidJwtAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.gestorinc.exception.enums.Error.*;
import static com.gestorinc.utils.Constants.ER;
import static com.gestorinc.utils.Constants.EXCEPTION_;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class ExceptionsAdvice {

    @ExceptionHandler({LogicBusinessException.class})
    public ResponseEntity<AbstractResponse> handleBusinessException(LogicBusinessException e) {
        return error(INTERNAL_SERVER_ERROR,
                e.getError(), e);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<AbstractResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return error(BAD_REQUEST,
                ERROR_EN_LOS_PARAMETROS_RECIBIDOS, e);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<AbstractResponse> handleBadCredentialsException(BadCredentialsException e) {
        return error(UNAUTHORIZED,
                ERROR_DE_AUTENTICACIÓN_DE_BANCO_CREDENCIALES_NO_VALIDAS, e);
    }

    @ExceptionHandler({InvalidJwtAuthenticationException.class})
    public ResponseEntity<AbstractResponse> handleInvalidJwtAuthenticationException(InvalidJwtAuthenticationException e) {
        return error(UNAUTHORIZED,
                ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO, e);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<AbstractResponse> handleRunTimeException(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR,
                HA_OCURRIDO_UN_ERROR_EN_EL_PROCESO_FAVOR_INTENTAR_MÁS_TARDE, e);
    }

    private ResponseEntity<AbstractResponse> error(HttpStatus status, Error error, Exception e) {
        log.error(EXCEPTION_, e);
        return ResponseEntity.status(status).body(new ErrorResponse(ER, error.getCodigo()));
    }
}