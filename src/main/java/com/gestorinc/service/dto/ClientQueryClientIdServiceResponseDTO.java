package com.gestorinc.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ClientQueryClientIdServiceResponseDTO extends AbstractServiceResponseDTO {
    private List<String> savingsFundAccount;

    @Builder
    public ClientQueryClientIdServiceResponseDTO(String participantAccountAud,
                                                 String productCodeAud,
                                                 List<String> savingsFundAccount) {
        super(participantAccountAud, productCodeAud);
        this.savingsFundAccount = savingsFundAccount;
    }
}
