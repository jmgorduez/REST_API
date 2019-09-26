package com.gestorinc.controller.abstracts;

import com.gestorinc.controller.model.ClientQueryRestControllerResponse;
import com.gestorinc.service.dto.ClientQueryClientIdServiceResponseDTO;
import com.gestorinc.service.dto.ClientQueryNPEServiceResponseDTO;

public interface IDTOMapper {
    ClientQueryRestControllerResponse buildClientQueryResponse(ClientQueryNPEServiceResponseDTO clientQueryClientIdResponseDTO);
    ClientQueryRestControllerResponse buildClientQueryResponse(ClientQueryClientIdServiceResponseDTO clientQueryClientIdResponseDTO);
}
