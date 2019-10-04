package com.gestorinc.repository.entity;

import com.gestorinc.repository.entity.enums.EnumEstadoIntencionAporte;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "FID_INTENCION_APORTE_BE")
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
    @Column(name = "COD_MONEDA")
    private String codigoMoneda;
    @Column(name = "CUENTA_PARTICIPE")
    private String cuentaParticipe;
    @Column(name = "ESTADO")
    @Enumerated(value = EnumType.STRING)
    private EnumEstadoIntencionAporte estado;

    public PersonaPK getPersonaPK(){
        return new PersonaPK(pk.numLicencia, codigoPersona);
    }
}
