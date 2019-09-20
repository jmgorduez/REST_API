package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "FID_SOLICITUD_APORTE")
@AllArgsConstructor
public class SolicitudAporte implements Serializable {
    @Id
    @Column(name = "ID")
    private Integer id;
}
