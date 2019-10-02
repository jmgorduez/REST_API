package com.gestorinc.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ClientQueryNPEServiceResponseDTO extends AbstractServiceResponseDTO {

    private String participantName;
    private String productNameToShow;
    private BigDecimal amount;

    @Builder
    public ClientQueryNPEServiceResponseDTO(String productCodeAud,
                                            String participantAccountAud,
                                            String participantName,
                                            String productNameToShow,
                                            BigDecimal amount){
        super(participantAccountAud, productCodeAud);
        this.productNameToShow = productNameToShow;
        this.participantName = participantName;
        this.amount = amount;
    }
}
