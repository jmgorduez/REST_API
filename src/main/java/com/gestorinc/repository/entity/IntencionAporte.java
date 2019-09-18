package com.gestorinc.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "FID_INTENCION_APORTE")
public class IntencionAporte {
    @Id
    @Column(name = "ID")
    @Getter
    @Setter
    private Integer id;

    @Column(name = "NPE")
    @Getter
    @Setter
    private Integer nPE;
}
