package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class ContributionConfirmationRequest {

    private final Long correlativo;
    private final String referencia;

    public ContributionConfirmationRequest(@JsonProperty("correlativo") Long correlativo,
                                           @JsonProperty("referencia") String referencia) {
        this.correlativo = correlativo;
        this.referencia = referencia;
    }
}
