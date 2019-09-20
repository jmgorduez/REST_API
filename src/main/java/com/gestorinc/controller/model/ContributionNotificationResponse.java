package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class NotificacionPagoAporteResponse extends AbstractResponse {

    private final Long correlativo;

    @Builder
    public NotificacionPagoAporteResponse(@JsonProperty("correlativo") Long correlativo,
                                          @JsonProperty("respuesta") String respuesta,
                                          @JsonProperty("error") String error) {
        super(respuesta, error);
        this.correlativo = correlativo;
    }
}
