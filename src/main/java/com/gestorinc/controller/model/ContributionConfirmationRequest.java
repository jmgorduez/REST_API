package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class ConfirmacionAporteRequest {

    private final Long correlativo;
    private final String referencia;
    private final String cuentaBancariaDestino;

    public ConfirmacionAporteRequest(@JsonProperty("correlativo") Long correlativo,
                                     @JsonProperty("referencia") String referencia,
                                     @JsonProperty("cuentaBancariaDestino") String cuentaBancariaDestino) {
        this.correlativo = correlativo;
        this.referencia = referencia;
        this.cuentaBancariaDestino = cuentaBancariaDestino;
    }
}
