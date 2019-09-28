package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ContributionNotificationRestControllerResponse extends AbstractRestControllerResponse {

    private final Long correlativo;

    @Builder
    public ContributionNotificationRestControllerResponse(@JsonProperty("correlativo") Long correlativo,
                                                          @JsonProperty("respuesta") String respuesta) {
        super(respuesta);
        this.correlativo = correlativo;
    }
}