package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ContributionConfirmationRestControllerResponse extends AbstractRestControllerResponse {

    @Builder
    public ContributionConfirmationRestControllerResponse(@JsonProperty("respuesta") String respuesta,
                                                          @JsonProperty("error") String error) {
        super(respuesta);
    }
}
