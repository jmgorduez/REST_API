package com.gestorinc.controller;

import com.gestorinc.controller.abstracts.IDTOMapper;
import com.gestorinc.controller.abstracts.IInteractionLogManager;
import com.gestorinc.controller.model.ClientQueryRestControllerRequest;
import com.gestorinc.controller.model.ClientQueryRestControllerResponse;
import com.gestorinc.service.abstractions.IClientQueryService;
import com.gestorinc.service.dto.ClientQueryClientIdServiceResponseDTO;
import com.gestorinc.service.dto.ClientQueryNPEServiceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;

import static com.gestorinc.utils.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

@Component
public class DTOMapper implements IDTOMapper {


    public ClientQueryRestControllerResponse buildClientQueryResponse(ClientQueryNPEServiceResponseDTO clientQueryClientIdResponseDTO) {

        return ClientQueryRestControllerResponse.builder()
                .nombre(clientQueryClientIdResponseDTO.getName())
                .fondo(clientQueryClientIdResponseDTO.getProduct())
                .monto(clientQueryClientIdResponseDTO.getAmount())
                .respuesta(OK)
                .build();
    }

    public ClientQueryRestControllerResponse buildClientQueryResponse(ClientQueryClientIdServiceResponseDTO clientQueryClientIdResponseDTO) {

        return ClientQueryRestControllerResponse.builder()
                .cuentaAPV(clientQueryClientIdResponseDTO.getSavingsFundAccount())
                .respuesta(OK)
                .build();
    }
}
