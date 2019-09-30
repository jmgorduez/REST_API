package com.gestorinc.controller;

import com.gestorinc.controller.model.AuthenticationResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.security.jwt.JwtTokenProvider;
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

import java.util.Objects;

import static com.gestorinc.utils.Constants.*;
import static java.util.Optional.ofNullable;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api(tags = AUTENTICACION)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = AUTENTICAR_USUARIO, response = AuthenticationResponse.class,
            notes = MUESTRA_EL_USUARIO_AUTENTICADO)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AUTENTICACIÓN_CORRECTA),
            @ApiResponse(code = 400, message = PARAMETROS_INCORRECTOS,
            response = ErrorRestControllerResponse.class)
    })
    @PostMapping(produces = APPLICATION_JSON, path = AUTENTICAR)
    public ResponseEntity<AuthenticationResponse> signin(
            @RequestParam(GRANT_TYPE)
            @NotEmpty
            @ApiParam(value = TIPO_DE_CONCESIÓN, required = true, defaultValue = CLIENT_CREDENTIALS) final String grantType,
            @RequestParam(CLIENT_ID)
            @ApiParam(value = CÓDIGO_DE_BANCO, required = true) final String username,
            @RequestParam(CLIENT_SECRET)
            @ApiParam(value = CONTRASEÑA_DE_ACCESO, required = true) final String password)
            throws MissingServletRequestParameterException {

        ofNullable(grantType)
                .filter(Objects::nonNull)
                .filter(param -> param.equals(CLIENT_CREDENTIALS))
                .orElseThrow(() ->
                        new MissingServletRequestParameterException(GRANT_TYPE, String.class.getTypeName()));

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            String token = jwtTokenProvider.createToken(username);

            return ok(AuthenticationResponse.builder()
                    .username(username)
                    .access_token(token)
                    .token_type(BEARED)
                    .expires_in(TOKEN_EXPIRES_IN)
                    .respuesta(OK)
                    .build());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(INVALID_USERNAME_PASSWORD_SUPPLIED);
        }
    }
}