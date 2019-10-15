package com.gestorinc.repository.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "AUD_INTERFAZ_LOG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInterfaz implements Serializable {

    public enum EstadoLog {OK, ER}

    @Id
    @Column(name = "ID")
    @NonNull
    private Long id;

    @Column(name = "USUARIO")
    @NonNull
    private String usuario;

    @Column(name = "OPERACION")
    @NonNull
    private String operacion;

    @Column(name = "FONDO")
    private String producto;

    @Column(name = "PARTICIPE")
    private String participe;

    @Column(name = "TRAMA_PEDIDO")
    private String tramaRequest;

    @Column(name = "TRAMA_RESPUESTA")
    private String tramaResponse;

    @Column(name = "ESTADO")
    @Enumerated(EnumType.STRING)
    @NotNull
    private EstadoLog estado;

    @Column(name = "MENSAJE")
    @NotNull
    private String mensaje;

    @Column(name = "FECHA_HORA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date fechaHoraRegistro;

    @Column(name = "IP")
    @NotNull
    private String ip;
}
