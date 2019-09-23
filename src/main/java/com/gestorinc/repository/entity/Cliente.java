package com.gestorinc.repository.entity;

import com.gestorinc.repository.entity.enums.EnumEstadoParticipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADM_CLIENTE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente implements Serializable {

    private static final long serialVersionUID = -2109414478011458398L;
    @EmbeddedId
    private ClientePK pk;
    @Column(name = "ESTADO_PARTICIPE", length = 3)
    @Enumerated(value = EnumType.STRING)
    private EnumEstadoParticipe estadoParticipe;
    @Column(name = "NUMERO_CUENTA", length = 30)
    private String numeroCuenta;
}