package com.gestorinc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "AUD_INTERFAZ_LOG")
@Getter
@Setter
@AllArgsConstructor
public class InterfazLog {

    public enum EstadoLog {OK, ER}

    @Id
    @Column(name = "ID")
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USUARIO")
    @NonNull
    private String usuario;

    @Column(name = "OPERACION")
    @NonNull
    private String operación;

    @Column(name = "FONDO")
    @NonNull
    private String fondo;

    @Column(name = "PARTICIPE")
    @NonNull
    private String participe;

    @Column(name = "TRAMA_PEDIDO")
    private String tramaPedido;

    @Column(name = "TRAMA_RESPUESTA")
    private String tramaRespuesta;

    @Column(name = "ESTADO")
    @Enumerated(EnumType.STRING)
    private EstadoLog estado;

    @Column(name = "MENSAJE")
    private String mensajeLog;
}
