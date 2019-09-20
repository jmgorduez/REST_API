package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class ClientQueryRequest extends AbstractRequest {

    public ClientQueryRequest(@JsonProperty("tipoIdentificador") String tipoIdentificador,
                              @JsonProperty("identificador") String identificador) {
       super(tipoIdentificador, identificador);
    }
}
