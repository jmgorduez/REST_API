package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import static com.gestorinc.utils.Constants.OK_ER;
import static com.gestorinc.utils.Constants.RESPUESTA_DE_LA_OPERACIÓN;

@Getter
public abstract class AbstractRestControllerResponse {

    @ApiModelProperty(notes = RESPUESTA_DE_LA_OPERACIÓN,
            required = true, allowableValues = OK_ER, position = 1)
    protected final String respuesta;

    public AbstractRestControllerResponse(@JsonProperty("respuesta") String respuesta) {
        this.respuesta = respuesta;
    }
}