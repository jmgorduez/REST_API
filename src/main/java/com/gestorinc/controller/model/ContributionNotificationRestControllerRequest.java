package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

import static com.gestorinc.utils.Constants.*;

@Getter
public final class ContributionNotificationRestControllerRequest extends AbstractRestControllerRequest {

    @ApiModelProperty(notes = FECHA_DEL_APORTE, required = true, position = 3)
    @NotNull
    private final Date fechaAporte;
    @ApiModelProperty(notes = MEDIO_DE_PAGO_DEL_APORTE, required = true,
            allowableValues = "1, 2, 3, 4, 5", position = 4)
    @NotNull
    private final Integer medioPago;
    @ApiModelProperty(notes = CUENTA_DEL_PARTICIPANTE, position = 5)
    private final String cuentaAPV;
    @ApiModelProperty(notes = MONTO_DEL_APORTE_SOLO_APLICA_CUANDO_SE_CONSULTA_POR_TIPO_IDENTIFICADOR_NPE,
            position = 6)
    private final BigDecimal monto;
    @ApiModelProperty(notes = CÓDIGO_GNL_DEL_FONDO_CÓDIGO_IDENTIFICADOR_DEL_FONDO,
            position = 7)
    private final Integer codigoGNL;

    @Builder
    public ContributionNotificationRestControllerRequest(@JsonProperty("tipoIdentificador") String tipoIdentificador,
                                                         @JsonProperty("identificador") String identificador,
                                                         @JsonProperty("fechaAporte") Date fechaAporte,
                                                         @JsonProperty("medioPago") Integer medioPago,
                                                         @JsonProperty("cuentaAPV") String cuentaAPV,
                                                         @JsonProperty("monto") BigDecimal monto,
                                                         @JsonProperty("codigoGNL") Integer codigoGNL) {
        super(tipoIdentificador, identificador);
        this.fechaAporte = fechaAporte;
        this.medioPago = medioPago;
        this.cuentaAPV = cuentaAPV;
        this.monto = monto;
        this.codigoGNL = codigoGNL;
    }
}
