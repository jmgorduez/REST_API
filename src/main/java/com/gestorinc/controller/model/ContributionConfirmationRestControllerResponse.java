package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContributionConfirmationRestControllerResponse extends AbstractRestControllerResponse {

    @Builder
    public ContributionConfirmationRestControllerResponse(@JsonProperty("respuesta") String respuesta) {
        super(respuesta);
    }
}