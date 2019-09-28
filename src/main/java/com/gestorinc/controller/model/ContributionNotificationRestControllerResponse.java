package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import static com.gestorinc.utils.Constants.CORRELATIVO_IDENTIFICADOR_DE_LA_NOTIFICACIÓN_DE_APORTE;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ContributionNotificationRestControllerResponse extends AbstractRestControllerResponse {

    @ApiModelProperty(notes = CORRELATIVO_IDENTIFICADOR_DE_LA_NOTIFICACIÓN_DE_APORTE,
            required = true, position = 2)
    private final Long correlativo;

    @Builder
    public ContributionNotificationRestControllerResponse(@JsonProperty("correlativo") Long correlativo,
                                                          @JsonProperty("respuesta") String respuesta) {
        super(respuesta);
        this.correlativo = correlativo;
    }
}