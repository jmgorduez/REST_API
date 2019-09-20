package com.gestorinc.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "FID_SOLICITUD_APORTE")
public class SolicitudAporte {
    @Id
    @Column(name = "ID")
    private Integer id;
}
