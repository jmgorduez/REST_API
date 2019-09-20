package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public final class NotificacionAporteRequest extends AbstractRequest {

    private final Date fechaAporte;
    private final Integer medioPago;
    private final String cuentaAPV;
    private final BigDecimal monto;
    private final String codigoGNL;

    public NotificacionAporteRequest(@JsonProperty("tipoIdentificador") String tipoIdentificador,
                                     @JsonProperty("identificador") String identificador,
                                     @JsonProperty("fechaAporte") Date fechaAporte,
                                     @JsonProperty("medioPago") Integer medioPago,
                                     @JsonProperty("cuentaAPV") String cuentaAPV,
                                     @JsonProperty("monto") BigDecimal monto,
                                     @JsonProperty("codigoGNL") String codigoGNL) {
        super(tipoIdentificador, identificador);
        this.fechaAporte = fechaAporte;
        this.medioPago = medioPago;
        this.cuentaAPV = cuentaAPV;
        this.monto = monto;
        this.codigoGNL = codigoGNL;
    }
}
