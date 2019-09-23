package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public abstract class AbstractRestControllerResponse {


    protected final String respuesta;

    public AbstractRestControllerResponse(@JsonProperty("respuesta") String respuesta) {
        this.respuesta = respuesta;
    }
}
