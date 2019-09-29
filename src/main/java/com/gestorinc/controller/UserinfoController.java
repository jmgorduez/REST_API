package com.gestorinc.controller;

import com.gestorinc.controller.model.UserinfoResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.gestorinc.utils.Constants.*;
import static com.gestorinc.utils.Constants.AUTHENTICATION_HEADER_PARAM;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api(tags = AUTENTICACION)
public class UserinfoController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @ApiOperation(value = CONSULTAR_USUARIO_AUTENTICADO, response = UserinfoResponse.class,
            notes = MUESTRA_EL_USUARIO_AUTENTICADO)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = USUARIO_AUTENTICADO_ENCONTRADO),
            @ApiResponse(code = 401, message = DEBE_AUTENTICARSE_USANDO_AUTENTICAR_Y_ENVIAR_EL_TOKEN_RECIBIDO_VIA_HEADER_AUTHENTICATION_BEARER_TOKEN),
            @ApiResponse(code = 400, message = PARAMETROS_INCORRECTOS)
    })
    @GetMapping(produces = APPLICATION_JSON, path = USUARIO_AUTENTICADO)
    public ResponseEntity<UserinfoResponse> currentUser(){
        return ok(UserinfoResponse.builder()
                .username(httpServletRequest.getUserPrincipal().getName())
                .build());
    }
}