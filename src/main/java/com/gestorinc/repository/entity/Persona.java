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
}