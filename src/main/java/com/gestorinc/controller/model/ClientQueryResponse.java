package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public final class ClientQueryResponse extends AbstractResponse {


    private final String nombre;
    private final String fondo;
    private final BigDecimal monto;
    private final List<String> cuentaAPV;

    @Builder
    public ClientQueryResponse(@JsonProperty("nombre") String nombre,
                               @JsonProperty("fondo") String fondo,
                               @JsonProperty("monto") BigDecimal monto,
                               @JsonProperty("cuentaAPV") List<String>cuentaAPV,
                               @JsonProperty("resultado") String respuesta,
                               @JsonProperty("error") String error) {
        super(respuesta, error);
        this.nombre = nombre;
        this.fondo = fondo;
        this.cuentaAPV = cuentaAPV;
        this.monto = monto;
    }
}
