package com.gestorinc.service.abstractions;

import com.gestorinc.controller.model.ClientQueryRestControllerResponse;
import com.gestorinc.controller.model.ContributionNotificationRestControllerResponse;
import com.gestorinc.service.dto.ClientQueryClientIdServiceResponseDTO;
import com.gestorinc.service.dto.ClientQueryNPEServiceResponseDTO;
import com.gestorinc.service.dto.ContributionNotificationServiceResponseDTO;

public interface IDTOMapper {
    ClientQueryRestControllerResponse buildClientQueryResponse(ClientQueryNPEServiceResponseDTO clientQueryClientIdResponseDTO);
    ClientQueryRestControllerResponse buildClientQueryResponse(ClientQueryClientIdServiceResponseDTO clientQueryClientIdResponseDTO);
    ContributionNotificationRestControllerResponse buildContributionNotificationResponse(
            ContributionNotificationServiceResponseDTO contributionNotificationServiceResponseDTO);
}
