package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class ConsultaClienteRequest extends AbstractRequest {

    public ConsultaClienteRequest(@JsonProperty("tipoIdentificador") String tipoIdentificador,
                                  @JsonProperty("identificador") String identificador) {
       super(tipoIdentificador, identificador);
    }
}
