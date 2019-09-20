package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ConfirmacionAporteResponse extends AbstractResponse {


    public ConfirmacionAporteResponse(@JsonProperty("respuesta") String respuesta,
                                      @JsonProperty("error") String error) {
        super(respuesta, error);
    }
}
