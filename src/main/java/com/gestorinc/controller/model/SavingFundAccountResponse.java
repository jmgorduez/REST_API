package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SavingFundAccountResponse {

    private String descripcionCuenta;
    private String numeroCuenta;
    private String codigoGLN;

    public SavingFundAccountResponse(@JsonProperty("descripcionCuenta") String descripcionCuenta,
                                     @JsonProperty("numeroCuenta") String numeroCuenta,
                                     @JsonProperty("codigoGLN") String codigoGLN){
        this.descripcionCuenta = descripcionCuenta;
        this.numeroCuenta = numeroCuenta;
        this.codigoGLN = codigoGLN;
    }
}
