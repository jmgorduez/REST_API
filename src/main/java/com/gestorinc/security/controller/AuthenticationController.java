package com.gestorinc.security.controller;

import com.gestorinc.security.controller.model.AuthenticationRequest;
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

import java.util.HashMap;
import java.util.Map;

import static com.gestorinc.utils.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider){
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(AUTH_SIGNIN)
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {
        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider
                    .createToken(username);
            return ok(getModel(username, token));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(INVALID_USERNAME_PASSWORD_SUPPLIED);
        }
    }

    private Map<Object, Object> getModel(String username, String token) {
        Map<Object, Object> model = new HashMap<>();
        model.put(USERNAME, username);
        model.put(TOKEN, token);
        return model;
    }
}