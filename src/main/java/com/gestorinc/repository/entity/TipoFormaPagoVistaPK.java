package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoFormaPagoVistaPK implements Serializable {

    @Column(name = "NUM_LICENCIA")
    private Integer numLicencia;
    @Column(name = "COD_TIPO_FORMA_PAGO")
    private String codigoTipoFormaPago;
}