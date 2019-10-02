package com.gestorinc.repository.entity;

import com.gestorinc.repository.entity.enums.EnumTipoFormaPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TES_V_TIPO_FORMA_PAGO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoFormaPagoVista implements Serializable {

    private static final long serialVersionUID = 6408180980053676676L;
    @EmbeddedId
    private TipoFormaPagoVistaPK pk;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "TIPO_FORMA_PAGO")
    @Enumerated(EnumType.STRING)
    private EnumTipoFormaPago tipoFormaPago;
    @Column(name = "DIAS_EFECTIVIZA_FORMA_PAGO")
    private Long diasEfectivizaFormaPago;
}