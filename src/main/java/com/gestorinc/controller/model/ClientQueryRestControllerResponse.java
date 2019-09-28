package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

import static com.gestorinc.utils.Constants.*;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public final class ClientQueryRestControllerResponse extends AbstractRestControllerResponse {

    @ApiModelProperty(notes = NOMBRE_DEL_PARTICIPANTE_SOLO_APLICA_CUANDO_SE_CONSULTA_POR_TIPO_IDENTIFICADOR_NPE,
            allowEmptyValue = true, position = 2)
    private final String nombre;
    @ApiModelProperty(notes = NOMBRE_DEL_FONDO_DE_AHORRO_SOLO_APLICA_CUANDO_SE_CONSULTA_POR_TIPO_IDENTIFICADOR_NPE,
            allowEmptyValue = true, position = 3)
    private final String fondo;
    @ApiModelProperty(notes = MONTO_DEL_APORTE_SOLO_APLICA_CUANDO_SE_CONSULTA_POR_TIPO_IDENTIFICADOR_NPE,
            allowEmptyValue = true, position = 4)
    private final BigDecimal monto;
    @ApiModelProperty(notes = LISTA_DE_CUENTAS_DE_AHORRO_SOLO_APLICA_CUANDO_SE_CONSULTA_POR_TIPO_IDENTIFICADOR_ID,
            allowEmptyValue = true, position = 5)
    private final List<SavingFundAccountResponse> cuentaAPV;

    @Builder
    public ClientQueryRestControllerResponse(@JsonProperty("nombre") String nombre,
                                             @JsonProperty("fondo") String fondo,
                                             @JsonProperty("monto") BigDecimal monto,
                                             @JsonProperty("cuentaAPV") List<SavingFundAccountResponse> cuentaAPV,
                                             @JsonProperty("resultado") String respuesta) {
        super(respuesta);
        this.nombre = nombre;
        this.fondo = fondo;
        this.cuentaAPV = cuentaAPV;
        this.monto = monto;
    }
}