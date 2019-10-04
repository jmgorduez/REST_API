package com.gestorinc.service;

import com.gestorinc.controller.model.ClientQueryRestControllerResponse;
import com.gestorinc.controller.model.ContributionConfirmationRestControllerResponse;
import com.gestorinc.controller.model.ContributionNotificationRestControllerResponse;
import com.gestorinc.controller.model.SavingFundAccountResponse;
import com.gestorinc.service.abstractions.IDTOMapper;
import com.gestorinc.service.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.gestorinc.utils.Constants.OK;

@Component
public class DTOMapper implements IDTOMapper {


    @Override
    public ClientQueryRestControllerResponse buildClientQueryResponse(ClientQueryNPEServiceResponseDTO clientQueryClientIdResponseDTO) {

        return ClientQueryRestControllerResponse.builder()
                .nombre(clientQueryClientIdResponseDTO.getParticipantName())
                .fondo(clientQueryClientIdResponseDTO.getProductNameToShow())
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

    @Override
    public ContributionNotificationRestControllerResponse buildContributionNotificationResponse(
            ContributionNotificationServiceResponseDTO contributionNotificationServiceResponseDTO) {
        return ContributionNotificationRestControllerResponse.builder()
                .correlativo(contributionNotificationServiceResponseDTO.getCorrelative())
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
