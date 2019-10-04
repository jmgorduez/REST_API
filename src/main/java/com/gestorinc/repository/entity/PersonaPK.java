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
public class PersonaPK implements Serializable {

    private static final long serialVersionUID = 4215503506429597016L;
    @Column(name = "NUM_LICENCIA", nullable = false, length = 10)
    private Integer numLicencia;
    @Column(name = "COD_PERSONA", nullable = false, length = 8)
    private Long codigoPersona;
}