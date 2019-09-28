package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public abstract class AbstractRestControllerRequest {

    @NotNull
    protected final String tipoIdentificador;
    @NotNull
    protected final String identificador;

    public AbstractRestControllerRequest(@JsonProperty("tipoIdentificador") String tipoIdentificador,
                                         @JsonProperty("identificador") String identificador) {
        this.tipoIdentificador = tipoIdentificador;
        this.identificador = identificador;
    }
}
