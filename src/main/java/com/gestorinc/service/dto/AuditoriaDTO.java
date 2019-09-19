package com.gestorinc.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuditoriaDTO {
    private String usuario;
    private String operacion;
    private String fondo;
    private String producto;
    private String ip;
    private String jsonTramaRequest;
    private String jsonTramaResponse;
}
