package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorRestControllerResponse extends AbstractRestControllerResponse {

    protected final String error;

    @Builder
    public ErrorRestControllerResponse(@JsonProperty("respuesta") String respuesta,
                                       @JsonProperty("error") String error) {
        super(respuesta);
        this.error = error;
    }
}