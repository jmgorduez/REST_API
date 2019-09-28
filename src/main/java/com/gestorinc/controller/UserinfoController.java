package com.gestorinc.controller;

import com.gestorinc.controller.model.AuthenticationResponse;
import com.gestorinc.controller.model.UserinfoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.gestorinc.utils.Constants.USERNAME;
import static com.gestorinc.utils.Constants.USUARIO_AUTENTICADO;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class UserinfoController {

    @GetMapping(USUARIO_AUTENTICADO)
    public ResponseEntity<UserinfoResponse> currentUser(@AuthenticationPrincipal UserDetails userDetails){
        return ok(UserinfoResponse.builder()
                .username(userDetails.getUsername())
                .build());
    }
}