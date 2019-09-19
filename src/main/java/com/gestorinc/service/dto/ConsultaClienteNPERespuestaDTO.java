package com.gestorinc.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ConsultaClienteNPERespuestaDTO {
    private String nombre;
    private String fondo;
    private BigDecimal monto;
}
