package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class ConsultaClienteResponse extends AbstractResponse {


    private final String nombre;
    private final String fondo;
    private final List<String> cuentaAPV;

    @Builder
    public ConsultaClienteResponse(@JsonProperty("nombre") String nombre,
                                   @JsonProperty("fondo") String fondo,
                                   @JsonProperty("cuentaAPV") List<String> cuentaAPV,
                                   @JsonProperty("resultado") String respuesta,
                                   @JsonProperty("error") String error) {
        super(respuesta, error);
        this.nombre = nombre;
        this.fondo = fondo;
        this.cuentaAPV = cuentaAPV;
    }
}
