package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Embeddable
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductoPK implements Serializable {

    private static final long serialVersionUID = 7311382423944057682L;
    @Column(name = "NUM_LICENCIA", nullable = false, length = 10)
    protected Integer numLicencia;
    @Column(name = "COD_EMPRESA", nullable = false, length = 15)
    protected String codigoEmpresa;
    @Column(name = "COD_PRODUCTO", nullable = false, length = 15)
    protected String codigoProducto;
}