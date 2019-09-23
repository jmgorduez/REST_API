package com.gestorinc.repository.entity;

import com.gestorinc.repository.entity.enums.EnumEstadoIntencionAporte;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "FID_INTENCION_APORTE")
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Column(name = "CUENTA_PARTICIPE")
    private String cuentaParticipe;
    @Column(name = "ESTADO")
    private EnumEstadoIntencionAporte estado;

    public PersonaPK getPersonaPK(){
        return new PersonaPK(pk.numLicencia, codigoPersona);
    }
}
