package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "FID_INTENCION_APORTE")
@AllArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NPE")
    private String nPE;

    @Id
    @Column(name = "COD_PERSONA")
    private Long codigoPersona;

    @Id
    @Column(name = "COD_PRODUCTO")
    private String codigoProducto;

    @Id
    @Column(name = "MONTO")
    private BigDecimal monto;
}
