package com.gestorinc.controller;

import com.gestorinc.controller.model.AuthenticationRequest;
import com.gestorinc.controller.model.AuthenticationResponse;
import com.gestorinc.security.jwt.JwtTokenProvider;
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
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(AUTENTICACION)
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody AuthenticationRequest data) {
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