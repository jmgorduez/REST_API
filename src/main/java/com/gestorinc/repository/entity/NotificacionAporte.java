package com.gestorinc.repository.entity;

import com.gestorinc.repository.entity.enums.EnumEstadoIntencionAporte;
import com.gestorinc.repository.entity.enums.EnumEstadoNotificacionAporte;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "FD_NOTIFICACION_APORTE_BE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotificacionAporte implements Serializable {

    @EmbeddedId
    private NotificacionAportePK pk;
    @Column(name = "NPE")
    private String nPE;
    @Column(name = "COD_PERSONA", nullable = false)
    private Long codigoPersona;
    @Column(name = "MONTO", nullable = false)
    private BigDecimal monto;
    @Column(name = "COD_MONEDA", nullable = false)
    private String codigoMoneda;
    @Column(name = "NUMERO_CUENTA", nullable = false)
    private String numeroCuenta;
    @Column(name = "ESTADO", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EnumEstadoNotificacionAporte estado;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_APORTE", nullable = false)
    private Date fechaHoraAporte;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_REG", nullable = false)
    private Date fechaHoraRegistro;
    @Column(name = "COD_FORMA_PAGO", nullable = false)
    private String codigoFormaPago;
}
