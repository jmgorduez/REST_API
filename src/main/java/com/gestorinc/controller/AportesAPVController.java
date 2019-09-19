package com.gestorinc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestorinc.controller.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.gestorinc.utils.Constants.*;
import static com.gestorinc.utils.HttpRequestUtil.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AportesAPVController {

    @Autowired
    public AportesAPVController() {
    }

    @PostMapping(produces = APPLICATION_JSON, path = V1_CONSULTAR_CLIENTE)
    public ResponseEntity<ConsultaClienteResponse> consultarCliente(@NotNull @RequestBody final ConsultaClienteRequest consultaClienteRequest)
            throws JsonProcessingException {
        String ip = clientIpAddress();
        String user = authenticatedBank();
        String json= jsonRequest(consultaClienteRequest);

        return ok(
                ConsultaClienteResponse.builder()
                        .fondo(json)
                        .nombre("")
                        .respuesta(OK)
                        .build());
    }

    private String jsonRequest(AbstractRequest body)
            throws JsonProcessingException {
        return new ObjectMapper()
                .writeValueAsString(body);
    }

    @PutMapping(produces = APPLICATION_JSON, path = V1_NOTIFICAR_APORTE)
    public ResponseEntity<NotificacionPagoAporteResponse> notificarAporte(@NotNull @RequestBody final ConsultaClienteRequest notiConsultaClienteRequest) {
        return null;
    }

    @PutMapping(produces = APPLICATION_JSON, path = V1_CONFIRMAR_APORTE)
    public ResponseEntity<ConfirmacionAporteResponse> confirmarAporte(@NotNull @RequestBody final ConfirmacionAporteRequest confirmacionAporteRequest) {
        return null;
    }
}
