package com.gestorinc.repository.entity;

import com.gestorinc.repository.entity.enums.EnumSiNo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ADM_PERSONA")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Persona implements Serializable {

    private static final long serialVersionUID = 7597916494789113677L;
    @EmbeddedId
    private PersonaPK pk;
    @Column(name = "NOMBRES", nullable = false, length = 500)
    private String nombres;
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private Date fechaNacimiento;
    @Column(name = "PAIS_LOCAL", nullable = false, length = 1)
    @Enumerated(value = EnumType.STRING)
    private EnumSiNo local;
}