package com.gestorinc.repository.entity;

import com.gestorinc.repository.entity.enums.EnumTipoCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ClientePK implements Serializable {

    private static final long serialVersionUID = 9153355890212827113L;


    @Column(name = "NUM_LICENCIA", nullable = false, length = 10)
    private Integer numLicencia;

    @Column(name = "COD_EMPRESA", nullable = false, length = 15)
    private String codigoEmpresa;

    @Column(name = "COD_PRODUCTO", nullable = false, length = 15)
    private String codigoProducto;

    @Column(name = "COD_PERSONA", nullable = false, length = 10)
    private Long codigoPersona;

    @Column(name = "TIPO_CLIENTE", nullable = false, length = 3)
    @Enumerated(value = EnumType.STRING)
    private EnumTipoCliente tipoCliente;

    @Column(name = "SEC_PERSONA_TIPO_CLIENTE", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer secuenciaPersonaTipoCliente;

    public ProductoPK getProductoPK() {
        return new ProductoPK(numLicencia, codigoEmpresa, codigoProducto);
    }
}