package com.gestorinc.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ClientQueryNPEServiceResponseDTO extends AbstractServiceResponseDTO {

    private String name;
    private String product;
    private BigDecimal amount;

    @Builder
    public ClientQueryNPEServiceResponseDTO(String productCodeAud, String participantAccountAud, String name, String product, BigDecimal amount){
        super(participantAccountAud, productCodeAud);
        this.product = product;
        this.name = name;
        this.amount = amount;
    }
}
