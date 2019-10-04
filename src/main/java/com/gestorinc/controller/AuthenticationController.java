package com.gestorinc.controller;

import com.gestorinc.controller.model.AuthenticationResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.security.jwt.JwtTokenProvider;
import com.gestorinc.security.service.abstracts.IEncryptionManager;
import io.swagger.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

import static com.gestorinc.utils.Constants.*;
import static java.util.Optional.ofNullable;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api(tags = AUTENTICACION)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private IEncryptionManager encryptionManager;

    @ApiOperation(value = AUTENTICAR_USUARIO, response = AuthenticationResponse.class,
            notes = MUESTRA_EL_USUARIO_AUTENTICADO)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AUTENTICACIÓN_CORRECTA),
            @ApiResponse(code = 400, message = PARAMETROS_INCORRECTOS,
                    response = ErrorRestControllerResponse.class),
            @ApiResponse(code = 401,
                    message = CREDENCIALES_INVALIDAS,
                    response = ErrorRestControllerResponse.class)
    })
    @PostMapping(consumes = APPLICATION_FORM_URLENCODED_VALUE,
            produces = APPLICATION_JSON, path = AUTENTICAR)
    public ResponseEntity<AuthenticationResponse> signin(
            @Valid @NotEmpty @RequestParam(GRANT_TYPE)
            @ApiParam(value = TIPO_DE_CONCESIÓN, required = true, defaultValue = CLIENT_CREDENTIALS) final String grantType,
            @Valid @NotEmpty @RequestParam(CLIENT_ID)
            @ApiParam(value = CÓDIGO_DE_BANCO, required = true) final String username,
            @Valid @NotEmpty @RequestParam(CLIENT_SECRET)
            @ApiParam(value = CONTRASEÑA_DE_ACCESO, required = true) final String password)
            throws MissingServletRequestParameterException {

        validateParamGrantType(grantType);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,
                            encryptionManager.encryptSHA512(password)));

            String token = jwtTokenProvider.createToken(username);

            return ok(AuthenticationResponse.builder()
                    .username(username)
                    .access_token(token)
                    .token_type(BEARER)
                    .expires_in(TOKEN_EXPIRES_IN)
                    .respuesta(OK)
                    .build());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(INVALID_USERNAME_PASSWORD_SUPPLIED);
        }
    }

    private void validateParamGrantType(String grantType) throws MissingServletRequestParameterException {
        ofNullable(grantType)
                .filter(Objects::nonNull)
                .filter(this::isClientCredentialsParam)
                .orElseThrow(this::throwMissingServletRequestParameterException);
    }

    private boolean isClientCredentialsParam(String param) {
        return param.equals(CLIENT_CREDENTIALS);
    }

    private MissingServletRequestParameterException throwMissingServletRequestParameterException() {
        return new MissingServletRequestParameterException(GRANT_TYPE, String.class.getTypeName());
    }
}