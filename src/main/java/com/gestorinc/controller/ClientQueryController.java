package com.gestorinc.controller;

import com.gestorinc.service.abstractions.IDTOMapper;
import com.gestorinc.service.abstractions.IInteractionLogManager;
import com.gestorinc.controller.model.ClientQueryRestControllerRequest;
import com.gestorinc.controller.model.ClientQueryRestControllerResponse;
import com.gestorinc.service.abstractions.IClientQueryService;
import com.gestorinc.service.dto.ClientQueryClientIdServiceResponseDTO;
import com.gestorinc.service.dto.ClientQueryNPEServiceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;

import static com.gestorinc.utils.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ClientQueryController {

    @Autowired
    private IClientQueryService clientQueryService;
    @Autowired
    private IInteractionLogManager logManager;
    @Autowired
    private IDTOMapper dtoMapper;

    @PostMapping(produces = APPLICATION_JSON, path = V1_CONSULTAR_CLIENTE)
    public ResponseEntity<ClientQueryRestControllerResponse> clientQuery(@NotNull @RequestBody final ClientQueryRestControllerRequest clientQueryRequest)
            throws IOException {

        if (clientQueryRequest.getTipoIdentificador().equals(NPE)) {

            return clientQueryByNPE(clientQueryRequest);
        } else {

            return clientQueryByClientId(clientQueryRequest);
        }
    }

    private ResponseEntity<ClientQueryRestControllerResponse> clientQueryByClientId(ClientQueryRestControllerRequest clientQueryRequest) throws IOException {

        ClientQueryClientIdServiceResponseDTO clientQueryClientIdResponseDTO
                = clientQueryService.queryByClientId(clientQueryRequest.getIdentificador());

        ClientQueryRestControllerResponse clientQueryResponse = dtoMapper.buildClientQueryResponse(clientQueryClientIdResponseDTO);

        logManager.generateAuditLog(clientQueryResponse,
                clientQueryClientIdResponseDTO, EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_ID_CLIENTE);

        return ok(clientQueryResponse);
    }

    private ResponseEntity<ClientQueryRestControllerResponse> clientQueryByNPE(ClientQueryRestControllerRequest clientQueryRequest) throws IOException {

        ClientQueryNPEServiceResponseDTO clientQueryNPEResponseDTO
                = clientQueryService.queryByNPE(clientQueryRequest.getIdentificador());

        ClientQueryRestControllerResponse clientQueryResponse = dtoMapper.buildClientQueryResponse(clientQueryNPEResponseDTO);

        logManager. generateAuditLog(clientQueryResponse,
                clientQueryNPEResponseDTO, EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_NPE);

        return ok(clientQueryResponse);
    }
}
