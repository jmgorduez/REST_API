package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "FID_INTENCION_APORTE")
@AllArgsConstructor
@Getter
@Setter
public class IntencionAporte implements Serializable {

    private static final long serialVersionUID = 7597916494789113677L;

    @EmbeddedId
    private IntencionAportePK pk;
    @Column(name = "NPE")
    private String nPE;
    @Column(name = "COD_PERSONA")
    private Long codigoPersona;
    @Column(name = "COD_PRODUCTO")
    private String codigoProducto;
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Column(name = "CUENTA_PARTICIPE")
    private String cuentaParticipe;

    public PersonaPK getPersonaPK(){
        return new PersonaPK(pk.numLicencia, codigoPersona);
    }
}
