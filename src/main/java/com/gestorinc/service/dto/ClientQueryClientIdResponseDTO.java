package com.gestorinc.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ConsultaClienteIdClienteRespuestaDTO {
    private List<String> cuentaAPV;
}
