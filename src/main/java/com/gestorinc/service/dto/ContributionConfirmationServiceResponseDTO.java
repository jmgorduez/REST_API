package com.gestorinc.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ContributionConfirmationServiceResponseDTO extends AbstractServiceResponseDTO {

    @Builder
    public ContributionConfirmationServiceResponseDTO(String productCodeAud,
                                                      String participantAccountAud){
        super(participantAccountAud, productCodeAud);
    }
}
