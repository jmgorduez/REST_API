package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import static com.gestorinc.utils.Constants.*;

@Getter
public class ErrorRestControllerResponse {

    @ApiModelProperty(notes = RESPUESTA_DE_LA_OPERACIÓN,
            required = true, allowableValues = ER, position = 1)
    protected final String respuesta = ER;
    @ApiModelProperty(notes = CÓDIGO_DE_ERROR,
            required = true, position = 2)
    protected final String error;

    @Builder
    public ErrorRestControllerResponse(@JsonProperty("error") String error) {
        this.error = error;
    }
}