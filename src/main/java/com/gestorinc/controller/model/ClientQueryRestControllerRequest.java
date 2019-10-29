package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public final class ClientQueryRestControllerRequest extends AbstractRestControllerRequest {

    public ClientQueryRestControllerRequest(@JsonProperty("tipoIdentificador") String tipoIdentificador,
                                            @JsonProperty("identificador") String identificador,
                                            @JsonProperty("codigoGLN") String codigoGLN) {
       super(tipoIdentificador, identificador, codigoGLN);
    }
}
