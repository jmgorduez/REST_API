package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public final class ConsultaClienteResponse {

    @Getter
    private final String nombre;
    @Getter
    private final String fondo;
    @Getter
    private final String cuentaAPV;
    @Getter
    private final String resultado;
    @Getter
    private final String error;

    public ConsultaClienteResponse(@JsonProperty("nombre") String nombre,
                                   @JsonProperty("fondo") String fondo,
                                   @JsonProperty("cuentaAPV") String cuentaAPV,
                                   @JsonProperty("resultado") String resultado,
                                   @JsonProperty("error") String error) {
        this.nombre = nombre;
        this.fondo = fondo;
        this.cuentaAPV = cuentaAPV;
        this.resultado = resultado;
        this.error = error;
    }
}
