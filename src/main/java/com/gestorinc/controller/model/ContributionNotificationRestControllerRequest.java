package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static com.gestorinc.utils.Constants.*;

@Getter
public final class ContributionNotificationRestControllerRequest
        extends AbstractRestControllerRequest {

    @ApiModelProperty(notes = FECHA_DEL_APORTE, required = true, position = 3)
    @NotNull
    private final String fechaAporte;
    @ApiModelProperty(notes = MEDIO_DE_PAGO_DEL_APORTE, required = true,
            allowableValues = "1, 2, 3, 4, 5", position = 4)
    @NotNull
    private final Integer medioPago;
    @ApiModelProperty(notes = CUENTA_DEL_PARTICIPANTE, position = 5)
    private final String cuentaAPV;
    @ApiModelProperty(notes = MONTO_DEL_APORTE_SOLO_APLICA_CUANDO_SE_CONSULTA_POR_TIPO_IDENTIFICADOR_NPE,
            position = 6)
    @DecimalMin("0.01")
    private final BigDecimal monto;

    @Builder
    public ContributionNotificationRestControllerRequest(@JsonProperty("tipoIdentificador") String tipoIdentificador,
                                                         @JsonProperty("identificador") String identificador,
                                                         @JsonProperty("fechaAporte") String fechaAporte,
                                                         @JsonProperty("medioPago") Integer medioPago,
                                                         @JsonProperty("cuentaAPV") String cuentaAPV,
                                                         @JsonProperty("monto") BigDecimal monto,
                                                         @JsonProperty("codigoGLN") Integer codigoGLN) {
        super(tipoIdentificador, identificador, codigoGLN);
        this.fechaAporte = fechaAporte;
        this.medioPago = medioPago;
        this.cuentaAPV = cuentaAPV;
        this.monto = monto;
    }
}
