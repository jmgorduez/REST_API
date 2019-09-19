package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public abstract class AbstractResponse {


    protected final String respuesta;
    protected final String error;

    public AbstractResponse(@JsonProperty("respuesta") String respuesta,
                            @JsonProperty("error") String error) {
        this.respuesta = respuesta;
        this.error = error;
    }
}
