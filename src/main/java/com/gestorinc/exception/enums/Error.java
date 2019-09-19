package com.gestorinc.exception.enums;

public enum Error {
    NPE_PAGADO("1"),
    NPE_VENCIDO("2"),
    NPE_NO_ENCONTRADO("3"),
    CLIENTE_NO_ENCONTRADO("4"),
    CLIENTE_NO_POSEE_CUENTA_ACTIVA("5"),
    HA_OCURRIDO_UN_ERROR_EN_EL_PROCESO_FAVOR_INTENTAR_MÁS_TARDE("6"),
    ERROR_DE_AUTENTICACIÓN_DE_BANCO_CREDENCIALES_NO_VALIDAS("7"),
    ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO("8"),
    CLIENTE_DEBE_ACTUALIZAR_SU_INFORMACIÓN_EN_CONFIA_PARA_PODER_REALIZAR_TRANSACCIONES("9"),
    ERROR_EN_LOS_PARAMETROS_RECIBIDOS("10"),
    NPE_NOTIFICADO("11"),
    CLIENTE_BLOQUEADO_POR_ACCIONES_LEGALES("12"),
    NO_EXISTE_UN_SOLICITUD_DE_APORTE_EN_ESTADO_INGRESADA_CON_ESTE_CORRELATIVO("13"),
    NO_EXISTE_LA_CUENTA_BANCARIA_DESTINO_REGISTRADA_PARA_EL_PRODUCTO_ACTUAL("14");

    private final String codigo;

    Error(String codigo){
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
