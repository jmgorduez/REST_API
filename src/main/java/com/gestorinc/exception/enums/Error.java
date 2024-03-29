package com.gestorinc.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    NPE_PAGADO_COD_1("1", "ERROR: NPE ya ha sido Pagado."),
    NPE_VENCIDO_COD_2("2", "ERROR: NPE se ha Vencido."),
    NPE_NO_ENCONTRADO_COD_3("3", "ERROR: NPE no encontrado."),
    CLIENTE_NO_ENCONTRADO_COD_4("4", "ERROR: Cliente no encontrado."),
    CLIENTE_NO_POSEE_CUENTA_ACTIVA_COD_5("5", "ERROR: Cliente no posee una cuenta de participe Activa."),
    HA_OCURRIDO_UN_ERROR_EN_EL_PROCESO_FAVOR_INTENTAR_MÁS_TARDE_COD_6("6", "ERROR: Ha ocurrido un error en el proceso, favor intente mas tarde"),
    ERROR_DE_AUTENTICACIÓN_DE_BANCO_CREDENCIALES_NO_VALIDAS_COD_7("7", "ERROR: Error de autenticacion, credenciales del banco invalidas."),
    ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8("8", "ERROR: Error de autenticacion, token no valido o expirado."),
    CLIENTE_DEBE_ACTUALIZAR_SU_INFORMACIÓN_EN_CONFIA_PARA_PODER_REALIZAR_TRANSACCIONES_COD_9("9", "ERROR: Cliente debe actualizar su informacion en Confia para poder realizar transacciones."),
    ERROR_EN_LOS_PARAMETROS_RECIBIDOS_COD_10("10", "ERROR: Error en los parametros recibidos."),
    NPE_NOTIFICADO_COD_11("11", "ERROR: NPE ya ha sido Notificado."),
    CLIENTE_BLOQUEADO_POR_ACCIONES_LEGALES_COD_12("12", "ERROR: Cliente bloqueado por acciones legales."),
    NO_EXISTE_UNA_NOTIFICACION_DE_APORTE_EN_ESTADO_INGRESADA_CON_ESTE_CORRELATIVO_COD_13("13", "ERROR: No existe una notificación de aporte en estado Ingresada con este correlativo."),
    FORMA_PAGO_NO_EXISTE_14("14", "ERROR: Forma de pago no existe."),
    PRODUCTO_CON_GLN_NO_ENCONTRADO("15", "ERROR: No existe un producto registrado con este GNL."),
    CLIENTE_NO_TIENE_CUENTA_REGISTRADA("16", "ERROR: Cliente no tiene la cuenta registrada."),
    FONDO_SIN_GLN_CONFIGURADO_18("18", "ERROR: No existe una configuracion de GLN para el fondo."),
    FORMATO_FECHA_INCORRECTO_19("19", "ERROR: El formato de fecha es incorrecto."),
    NPE_RESERVADO_20("20", "ERROR: NPE reservado."),
    NOTIFICACION_CONFIRMADA_21("21", "ERROR: Notificación ya ha sido confirmada."),
    NOTIFICACION_PROCESADA_22("22", "ERROR: Notificación ya ha sido procesada."),
    FORMATO_INCORRECTO_GLN_23("23", "ERROR: Formato incorrecto GLN."),
    FECHA_DIA_FERIADO_24("24", "ERROR: La fecha es un dia feriado.");

    private final String code;
    private final String message;
}
