package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import static com.gestorinc.utils.Constants.*;

@Builder
@Getter
public class SavingFundAccountResponse {

    @ApiModelProperty(notes = DESCRIPCIÓN_DE_LA_CUENTA_DEL_PARTICIPANTE_CAMPO_PENSADO_PARA_MOSTRAR_DE_CARA_AL_CLIENTE,
            allowEmptyValue = true, position = 1)
    private String descripcionCuenta;
    @ApiModelProperty(notes = NÚMERO_DE_CUENTA_DEL_PARTICIPANTE,
            allowEmptyValue = true, position = 2)
    private String numeroCuenta;
    @ApiModelProperty(notes = CÓDIGO_GNL_DEL_FONDO_CÓDIGO_IDENTIFICADOR_DEL_FONDO,
            allowEmptyValue = true, position = 3)
    private String codigoGLN;

    public SavingFundAccountResponse(@JsonProperty("descripcionCuenta") String descripcionCuenta,
                                     @JsonProperty("numeroCuenta") String numeroCuenta,
                                     @JsonProperty("codigoGLN") String codigoGLN){
        this.descripcionCuenta = descripcionCuenta;
        this.numeroCuenta = numeroCuenta;
        this.codigoGLN = codigoGLN;
    }
}
