package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public abstract class AbstractRestControllerRequest {

    protected final String tipoIdentificador;
    protected final String identificador;

    public AbstractRestControllerRequest(@JsonProperty("tipoIdentificador") String tipoIdentificador,
                                         @JsonProperty("identificador") String identificador) {
        this.tipoIdentificador = tipoIdentificador;
        this.identificador = identificador;
    }
}
