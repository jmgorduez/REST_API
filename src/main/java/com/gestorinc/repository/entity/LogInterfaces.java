package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "AUD_INTERFAZ_LOG")
@Getter
@Setter
@AllArgsConstructor
public class LogInterfaces implements Serializable {

    public enum EstadoLog {OK, ER}

    @Id
    @Column(name = "ID")
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USUARIO")
    @NonNull
    private String user;

    @Column(name = "OPERACION")
    @NonNull
    private String operation;

    @Column(name = "FONDO")
    @NonNull
    private String product;

    @Column(name = "PARTICIPE")
    @NonNull
    private String participant;

    @Column(name = "TRAMA_PEDIDO")
    private String requestFrame;

    @Column(name = "TRAMA_RESPUESTA")
    private String responseFrame;

    @Column(name = "ESTADO")
    @Enumerated(EnumType.STRING)
    private EstadoLog status;

    @Column(name = "MENSAJE")
    private String logMessage;
}
