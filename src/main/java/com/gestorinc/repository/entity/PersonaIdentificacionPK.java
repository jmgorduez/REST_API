package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class PersonaIdentificacionPK implements Serializable {

    private static final long serialVersionUID = 8226954080022153014L;
    @Column(name = "NUM_LICENCIA", nullable = false, length = 10)
    private Integer numLicencia;
    @Column(name = "COD_PERSONA", nullable = false, length = 10)
    private Long codigoPersona;
    @Column(name = "COD_TIPO_IFICACION", nullable = false, length = 15)
    private String codigoTipoIdentificacion;
}