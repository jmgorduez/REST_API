package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class IntencionAportePK implements Serializable {

    private static final long serialVersionUID = 7311382423944057682L;
    @Column(name = "NUM_LICENCIA", nullable = false, length = 10)
    protected Integer numLicencia;
    @Column(name = "COD_EMPRESA", nullable = false, length = 15)
    protected String codigoEmpresa;
    @Column(name = "COD_PRODUCTO", nullable = false, length = 15)
    protected String codigoProducto;
    @Column(name = "SEC_INTENCION", nullable = false, length = 15)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long secIntencion;
}
