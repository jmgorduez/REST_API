package com.gestorinc.controller;

import com.gestorinc.controller.abstracts.IDTOMapper;
import com.gestorinc.controller.model.ClientQueryRestControllerResponse;
import com.gestorinc.controller.model.SavingFundAccountResponse;
import com.gestorinc.service.dto.ClientQueryClientIdServiceResponseDTO;
import com.gestorinc.service.dto.ClientQueryNPEServiceResponseDTO;
import com.gestorinc.service.dto.SavingFundAccountDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.gestorinc.utils.Constants.OK;

@Component
public class DTOMapper implements IDTOMapper {


    @Override
    public ClientQueryRestControllerResponse buildClientQueryResponse(ClientQueryNPEServiceResponseDTO clientQueryClientIdResponseDTO) {

        return ClientQueryRestControllerResponse.builder()
                .nombre(clientQueryClientIdResponseDTO.getName())
                .fondo(clientQueryClientIdResponseDTO.getProduct())
                .monto(clientQueryClientIdResponseDTO.getAmount())
                .respuesta(OK)
                .build();
    }

    @Override
    public ClientQueryRestControllerResponse buildClientQueryResponse(ClientQueryClientIdServiceResponseDTO clientQueryClientIdResponseDTO) {

        List<SavingFundAccountResponse> savingFundAccountResponses =
                clientQueryClientIdResponseDTO.getSavingsFundAccount().stream()
                .map(this::toSavingFundAccountResponse)
                .collect(Collectors.toList());

        return ClientQueryRestControllerResponse.builder()
                .cuentaAPV(savingFundAccountResponses)
                .respuesta(OK)
                .build();
    }

    private SavingFundAccountResponse toSavingFundAccountResponse(SavingFundAccountDTO savingFundAccountDTO){
        return SavingFundAccountResponse.builder()
                .numeroCuenta(savingFundAccountDTO.getAccountNumber())
                .descripcionCuenta(savingFundAccountDTO.getAccountDescription())
                .codigoGLN(savingFundAccountDTO.getGLNCode())
                .build();
    }
}
