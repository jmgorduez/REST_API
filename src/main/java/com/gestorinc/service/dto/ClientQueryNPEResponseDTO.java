package com.gestorinc.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ClientQueryNPEResponseDTO {
    private String name;
    private String product;
    private BigDecimal amount;
}
