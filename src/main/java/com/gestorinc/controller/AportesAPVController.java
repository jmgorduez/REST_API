package com.gestorinc.controller;

import com.gestorinc.controller.model.ConsultaClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.gestorinc.utils.Constants.*;

@RestController
public class AportesAPVController {


    @Autowired
    public AportesAPVController(){
    }

    @PostMapping(V1_CONSULTAR_CLIENTE)
    public ResponseEntity<ConsultaClienteResponse> consultaCliente(@NotNull @RequestParam final String tipoIdentificador,
                                                                   @NotNull @RequestParam final String identificador,
                                                                   @NotNull @RequestParam final String codBanco,
                                                                   @NotNull @RequestParam final String ip,
                                                                   @NotNull @RequestParam final String token){
        return null;
    }

    @PutMapping(V1_NOTIFICAR_APORTE)
    public ResponseEntity<ConsultaClienteResponse> notificarAporte(){
        return null;
    }

    @PutMapping(V1_CONFIRMAR_APORTE)
    public ResponseEntity<ConsultaClienteResponse> confirmarAporte(){
        return null;
    }
}
