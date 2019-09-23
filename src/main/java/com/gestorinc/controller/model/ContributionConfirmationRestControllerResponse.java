package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ContributionConfirmationRestControllerResponse extends AbstractRestControllerResponse {


    public ContributionConfirmationRestControllerResponse(@JsonProperty("respuesta") String respuesta,
                                                          @JsonProperty("error") String error) {
        super(respuesta);
    }
}
