package com.gestorinc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestorinc.controller.model.*;
import com.gestorinc.service.abstractions.IClientQueryService;
import com.gestorinc.service.dto.ClientQueryNPEResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.gestorinc.utils.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class SavingsFundContributionsController {

    private IClientQueryService clientQueryService;

    @Autowired
    public SavingsFundContributionsController(IClientQueryService clientQueryService) {
        this.clientQueryService = clientQueryService;
    }

    @PostMapping(produces = APPLICATION_JSON, path = V1_CONSULTAR_CLIENTE)
    public ResponseEntity<ClientQueryResponse> clientQuery(@NotNull @RequestBody final ClientQueryRequest clientQueryRequest)
            throws JsonProcessingException {
        ClientQueryNPEResponseDTO clientQueryClientIdResponseDTO
                = clientQueryService.queryByNPE(clientQueryRequest.getIdentificador());
        return ok(buildClientQueryResponse(clientQueryClientIdResponseDTO));
    }

    private ClientQueryResponse buildClientQueryResponse(ClientQueryNPEResponseDTO clientQueryClientIdResponseDTO) {
        return ClientQueryResponse.builder()
                .nombre(clientQueryClientIdResponseDTO.getName())
                .fondo(clientQueryClientIdResponseDTO.getProduct())
                .monto(clientQueryClientIdResponseDTO.getAmount())
                .respuesta(OK)
                .build();
    }

    private String jsonRequest(AbstractRequest body)
            throws JsonProcessingException {
        return new ObjectMapper()
                .writeValueAsString(body);
    }

    @PutMapping(produces = APPLICATION_JSON, path = V1_NOTIFICAR_APORTE)
    public ResponseEntity<ContributionNotificationResponse> contributionNotification(@NotNull @RequestBody final ContributionNotificationRequest contributionNotificationRequest) {
        return null;
    }

    @PutMapping(produces = APPLICATION_JSON, path = V1_CONFIRMAR_APORTE)
    public ResponseEntity<ContributionConfirmationResponse> contributionConfirmation(@NotNull @RequestBody final ContributionConfirmationRequest contributionConfirmationRequest) {
        return null;
    }
}
