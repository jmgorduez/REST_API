package com.gestorinc.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.gestorinc.utils.Constants.ME;
import static com.gestorinc.utils.Constants.USERNAME;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class UserinfoController {

    @GetMapping(ME)
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put(USERNAME, userDetails.getUsername());
        return ok(model);
    }
}