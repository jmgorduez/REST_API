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
public class CredencialesBancariasPK implements Serializable {

    private static final long serialVersionUID = 8226954080022153014L;
    @Column(name = "NUM_LICENCIA", nullable = false, length = 10)
    private Integer numLicencia;
    @Column(name = "COD_TIPO_ENTIDAD", nullable = false, length = 8)
    private String codigoTipoEntidad;
    @Column(name = "COD_ENTIDAD", nullable = false, length = 8)
    private String codigoEntidad;
}