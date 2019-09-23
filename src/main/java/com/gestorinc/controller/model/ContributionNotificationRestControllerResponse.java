package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class ContributionNotificationRestControllerResponse extends AbstractRestControllerResponse {

    private final Long correlativo;

    @Builder
    public ContributionNotificationRestControllerResponse(@JsonProperty("correlativo") Long correlativo,
                                                          @JsonProperty("respuesta") String respuesta,
                                                          @JsonProperty("error") String error) {
        super(respuesta);
        this.correlativo = correlativo;
    }
}
