package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ADM_PERSONA_IDENTIFICACION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonaIdentificacion implements Serializable {

    private static final long serialVersionUID = 7955620518359150939L;

    @EmbeddedId
    private PersonaIdentificacionPK pk;
    @Column(name = "IDENTIFICACION", nullable = false, length = 30)
    private String identificacion;
}