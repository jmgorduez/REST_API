package com.gestorinc.controller;

import com.gestorinc.controller.model.AuthenticationRequest;
import com.gestorinc.controller.model.AuthenticationResponse;
import com.gestorinc.security.jwt.JwtTokenProvider;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.gestorinc.utils.Constants.*;
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
            @ApiResponse(code = 400, message = PARAMETROS_INCORRECTOS)
    })
    @PostMapping(produces = APPLICATION_JSON, path = AUTENTICAR)
    public ResponseEntity<AuthenticationResponse> signin(
            @RequestBody @ApiParam(value = ENTRADA_PARA_LA_AUTENTICACIÓN_DEL_USUARIO)
                    AuthenticationRequest data) {
        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));

            String token = jwtTokenProvider.createToken(username);

            return ok(AuthenticationResponse.builder()
                    .username(username)
                    .token(token)
                    .build());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(INVALID_USERNAME_PASSWORD_SUPPLIED);
        }
    }
}