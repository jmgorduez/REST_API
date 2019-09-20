package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ADM_PRODUCTO")
@AllArgsConstructor
@Getter
@Setter
public class Producto implements Serializable {

    private static final long serialVersionUID = -2941552295788165801L;
    @EmbeddedId
    private ProductoPK pk;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
}