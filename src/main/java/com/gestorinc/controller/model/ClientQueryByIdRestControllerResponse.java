package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public final class ClientQueryByIdRestControllerResponse extends AbstractRestControllerResponse {

    private final List<SavingFundAccountResponse> cuentaAPV;

    @Builder
    public ClientQueryByIdRestControllerResponse(@JsonProperty("cuentaAPV") List<SavingFundAccountResponse>cuentaAPV,
                                                 @JsonProperty("respuesta") String respuesta) {
        super(respuesta);
        this.cuentaAPV = cuentaAPV;
    }
}