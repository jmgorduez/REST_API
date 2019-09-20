package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ContributionConfirmationResponse extends AbstractResponse {


    public ContributionConfirmationResponse(@JsonProperty("respuesta") String respuesta,
                                            @JsonProperty("error") String error) {
        super(respuesta, error);
    }
}
