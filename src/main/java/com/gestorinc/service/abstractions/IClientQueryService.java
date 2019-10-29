package com.gestorinc.service.abstractions;

import com.gestorinc.service.dto.ClientQueryClientIdServiceResponseDTO;
import com.gestorinc.service.dto.ClientQueryNPEServiceResponseDTO;

public interface IClientQueryService {
    ClientQueryNPEServiceResponseDTO queryByNPE(String npe);
    ClientQueryClientIdServiceResponseDTO queryByClientId(String clientId, String gLNCode);
}
