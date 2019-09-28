package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import static com.gestorinc.utils.Constants.CORRELATIVO_IDENTIFICADOR_DE_LA_NOTIFICACIÓN_DE_APORTE;
import static com.gestorinc.utils.Constants.REFERENCIA_DE_LA_TRANSACCIÓN_BANCARIA;

@Getter
public final class ContributionConfirmationRequest {

    @ApiModelProperty(notes = CORRELATIVO_IDENTIFICADOR_DE_LA_NOTIFICACIÓN_DE_APORTE,
            required = true, position = 1)
    private final Long correlativo;
    @ApiModelProperty(notes = REFERENCIA_DE_LA_TRANSACCIÓN_BANCARIA,
            required = true, position = 2)
    private final String referencia;

    public ContributionConfirmationRequest(@JsonProperty("correlativo") Long correlativo,
                                           @JsonProperty("referencia") String referencia) {
        this.correlativo = correlativo;
        this.referencia = referencia;
    }
}
