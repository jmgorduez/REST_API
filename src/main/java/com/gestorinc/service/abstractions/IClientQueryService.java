package com.gestorinc.service.abstractions;

import com.gestorinc.service.dto.ClientQueryClientIdResponseDTO;
import com.gestorinc.service.dto.ClientQueryNPEResponseDTO;

public interface IClientQueryService {
    ClientQueryNPEResponseDTO queryByNPE(String npe);
    ClientQueryClientIdResponseDTO QueryByClientId(String clientId);
}
