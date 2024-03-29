package com.gestorinc.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ContributionNotificationServiceResponseDTO extends AbstractServiceResponseDTO {

    private Long correlative;

    @Builder
    public ContributionNotificationServiceResponseDTO(String productCodeAud,
                                                      String participantAccountAud,
                                                      Long correlative){
        super(participantAccountAud, productCodeAud);
        this.correlative = correlative;
    }
}
