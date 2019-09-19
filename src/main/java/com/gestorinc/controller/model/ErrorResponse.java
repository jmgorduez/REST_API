package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorResponse extends AbstractResponse {

    public ErrorResponse(@JsonProperty("respuesta") String respuesta,
                         @JsonProperty("error") String error) {
        super(respuesta, error);
    }
}
