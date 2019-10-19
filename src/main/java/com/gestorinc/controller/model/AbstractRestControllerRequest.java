package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

import static com.gestorinc.utils.Constants.*;

@Getter
public abstract class AbstractRestControllerRequest {

    @ApiModelProperty(notes = TIPO_DE_IDENTIFICADOR,
            required = true, allowableValues = NPE_ID, position = 1)
    @NotNull
    protected final String tipoIdentificador;
    @NotNull
    @ApiModelProperty(notes = IDENTIFICADOR, required = true, position = 2)
    protected final String identificador;
    @ApiModelProperty(notes = CÓDIGO_GNL_DEL_FONDO_CÓDIGO_IDENTIFICADOR_DEL_FONDO,
            position = 3)
    protected final Integer codigoGLN;

    public AbstractRestControllerRequest(@JsonProperty("tipoIdentificador") String tipoIdentificador,
                                         @JsonProperty("identificador") String identificador,
                                         @JsonProperty("codigoGLN") Integer codigoGLN) {
        this.tipoIdentificador = tipoIdentificador;
        this.identificador = identificador;
        this.codigoGLN = codigoGLN;
    }
}
