package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionAportePK implements Serializable {

    private static final long serialVersionUID = 7311382423944057682L;

    @Column(name = "NUM_LICENCIA", nullable = false, length = 10)
    private Integer numLicencia;
    @Column(name = "COD_EMPRESA", nullable = false, length = 15)
    private String codigoEmpresa;
    @Column(name = "COD_PRODUCTO", nullable = false, length = 15)
    private String codigoProducto;
    @Column(name = "SEC_NOTIFICACION", nullable = false, length = 15)
    private Long secNotificacion;
}
