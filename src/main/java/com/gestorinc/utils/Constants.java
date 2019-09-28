package com.gestorinc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.exception.enums.Error;

import static com.gestorinc.exception.enums.Error.*;

public class Constants {

    public static final String V1_CONSULTAR_CLIENTE = "/v1/consultarCliente";
    public static final String V1_NOTIFICAR_APORTE = "/v1/notificarAporte";
    public static final String V1_CONFIRMAR_APORTE = "/v1/confirmarAporte";
    public static final String AUTENTICACION = "/autenticar";
    public static final String USUARIO_AUTENTICADO = "/usuarioAutenticado";

    public static final String JWT_AUTHENTICATION_FAILED = "Jwt authentication failed";
    public static final String INVALID_USERNAME_PASSWORD_SUPPLIED = "Invalid username/password supplied";
    public static final String EXPIRED_OR_INVALID_JWT_TOKEN = "Expired or invalid JWT token";
    public static final String SECRET = "secret";
    public static final String $_SECURITY_JWT_TOKEN_SECRET_KEY_SECRET = "${security.jwt.token.secret-key:secret}";
    public static final String $_SECURITY_JWT_TOKEN_EXPIRE_LENGTH_3600000 = "${security.jwt.token.expire-length:3600000}";
    public static final String AUTHORIZATION = "Authorization";
    public static final String USERNAME = "username";
    public static final String TOKEN = "token";
    public static final String OK = "OK";
    public static final String ER = "ER";
    public static final String USER = "USER";
    public static final String EXCEPTION_ = "Exception: ";
    public static final String APPLICATION_JSON = "application/json";
    public static final String NPE = "NPE";
    public static final String ID = "ID";
    public static final String FONDO_ = "Fondo ";
    public static final String BLANK_SPACE = " ";
    public static final String _____ = "*****";
    public static final String EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_NPE = "Ejecución de consulta de cliente por NPE";
    public static final String EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_ID_CLIENTE = "Ejecución de consulta de cliente por Id cliente";
    public static final String DUI_CODE = "10";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String ANONYMOUS = "anonymous";

    public static final ObjectMapper
            OBJECT_MAPPER = new ObjectMapper();

    public static final ErrorRestControllerResponse ERROR_1_RESPONSE =
            errorResponse(NPE_PAGADO_COD_1);
    public static final ErrorRestControllerResponse ERROR_2_RESPONSE =
            errorResponse(NPE_VENCIDO_COD_2);
    public static final ErrorRestControllerResponse ERROR_3_RESPONSE =
            errorResponse(NPE_NO_ENCONTRADO_COD_3);;
    public static final ErrorRestControllerResponse ERROR_4_RESPONSE =
            errorResponse(CLIENTE_NO_ENCONTRADO_COD_4);
    public static final ErrorRestControllerResponse ERROR_5_RESPONSE =
            errorResponse(CLIENTE_NO_POSEE_CUENTA_ACTIVA_COD_5);
    public static final ErrorRestControllerResponse ERROR_6_RESPONSE =
            errorResponse(HA_OCURRIDO_UN_ERROR_EN_EL_PROCESO_FAVOR_INTENTAR_MÁS_TARDE_COD_6);
    public static final ErrorRestControllerResponse ERROR_7_RESPONSE =
            errorResponse(ERROR_DE_AUTENTICACIÓN_DE_BANCO_CREDENCIALES_NO_VALIDAS_COD_7);
    public static final ErrorRestControllerResponse ERROR_8_RESPONSE =
            errorResponse(ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8);
    public static final ErrorRestControllerResponse ERROR_9_RESPONSE =
            errorResponse(CLIENTE_DEBE_ACTUALIZAR_SU_INFORMACIÓN_EN_CONFIA_PARA_PODER_REALIZAR_TRANSACCIONES_COD_9);
    public static final ErrorRestControllerResponse ERROR_11_RESPONSE =
            errorResponse(NPE_NOTIFICADO_COD_11);

    public static ErrorRestControllerResponse errorResponse(Error error){
        return ErrorRestControllerResponse.builder()
                .respuesta(ER)
                .error(error.getCode())
                .build();
    }

    public static final String NOMBRE_DEL_PARTICIPANTE_SOLO_APLICA_CUANDO_SE_CONSULTA_POR_TIPO_IDENTIFICADOR_NPE = "Nombre del participante. Solo aplica cuando se consulta por tipoIdentificador NPE.";
    public static final String NOMBRE_DEL_FONDO_DE_AHORRO_SOLO_APLICA_CUANDO_SE_CONSULTA_POR_TIPO_IDENTIFICADOR_NPE = "Nombre del fondo de ahorro. Solo aplica cuando se consulta por tipoIdentificador NPE.";
    public static final String MONTO_DEL_APORTE_SOLO_APLICA_CUANDO_SE_CONSULTA_POR_TIPO_IDENTIFICADOR_NPE = "Monto del aporte. Solo aplica cuando se consulta por tipoIdentificador NPE.";
    public static final String LISTA_DE_CUENTAS_DE_AHORRO_SOLO_APLICA_CUANDO_SE_CONSULTA_POR_TIPO_IDENTIFICADOR_ID = "Lista de cuentas de ahorro. Solo aplica cuando se consulta por tipoIdentificador ID.";
    public static final String RESPUESTA_DE_LA_OPERACIÓN = "Respuesta de la operación.";
    public static final String DESCRIPCIÓN_DE_LA_CUENTA_DEL_PARTICIPANTE_CAMPO_PENSADO_PARA_MOSTRAR_DE_CARA_AL_CLIENTE = "Descripción de la cuenta del participante, campo pensado para mostrar de cara al cliente.";
    public static final String NÚMERO_DE_CUENTA_DEL_PARTICIPANTE = "Número de cuenta del participante, identificador de la cuenta del participante.";
    public static final String CÓDIGO_GNL_DEL_FONDO_CÓDIGO_IDENTIFICADOR_DEL_FONDO = "Código GNL del fondo, código identificador del fondo.";
    public static final String TIPO_DE_IDENTIFICADOR = "Tipo de identificador.";
    public static final String IDENTIFICADOR = "Identificador.";
    public static final String NPE_ID = "NPE, ID";
    public static final String OK_ER = "OK, ER";
    public static final String CÓDIGO_DE_BANCO = "Código de banco.";
    public static final String CONTRASEÑA_DE_ACCESO = "Contraseña de acceso.";
    public static final String CORRELATIVO_IDENTIFICADOR_DE_LA_NOTIFICACIÓN_DE_APORTE = "Correlativo identificador de la notificación de aporte.";
    public static final String REFERENCIA_DE_LA_TRANSACCIÓN_BANCARIA = "Referencia de la transacción bancaria.";
    public static final String FECHA_DEL_APORTE = "Fecha del aporte";
    public static final String MEDIO_DE_PAGO_DEL_APORTE = "Medio de pago del aporte";
    public static final String CUENTA_DEL_PARTICIPANTE = "Cuenta del participante.";
    public static final String MUESTRA_INFORMACIÓN_DEL_NPE_O_DE_LAS_CUENTAS_DEL_PARTICIPANTE_EN_DEPENDENCIA_DEL_TIPO_IDENTIFICADOR_RECIBIDO
            = "Muestra información del NPE o de las Cuentas del participante en dependencia del tipoIdentificador recibido.";
    public static final String INFORMACIÓN_DEL_NPE_O_LA_CUENTA_DEL_PARTICIPANTE_ENCONTRADA
            = "Información del NPE o la Cuenta del participante encontrada.";
    public static final String CONFIRMA_EL_PAGO_DE_LA_NOTIFICACIÓN_DE_APORTE = "Confirma el pago de la notificación de aporte.";
    public static final String CONFIRMADA_LA_NOTIFICACIÓN_DE_APORTE = "Confirmada la notificación de aporte.";
    public static final String REGISTRA_UNA_NOTIFICACION_DE_APORTE = "Registra una notificacion de aporte";
    public static final String CREADA_NOTIFICACIÓN_DE_APORTE = "Creada notificación de aporte";
    public static final String CONSULTAR_CLIENTE = "Consultar cliente";
    public static final String TOKEN_DE_ACCESO_OBTENIDO_LUEGO_DE_AUTENTICARSE = "Token de acceso obtenido luego de autenticarse.";
    public static final String STRING = "string";
    public static final String CONSULTAR_USUARIO_AUTENTICADO = "Consultar usuario autenticado";
    public static final String MUESTRA_EL_USUARIO_AUTENTICADO = "Muestra el usuario autenticado.";
    public static final String USUARIO_AUTENTICADO_ENCONTRADO = "Usuario autenticado encontrado.";
    public static final String AUTHENTICATION_HEADER_PARAM = "Authentication header param";
    public static final String AUTENTICACIÓN_CORRECTA = "Autenticación correcta.";
    public static final String AUTENTICAR_USUARIO = "Autenticar usuario";
    public static final String ENTRADA_PARA_LA_CONSULTA_DE_CLIENTE = "Entrada para la consulta de cliente.";
    public static final String ENTRADA_PARA_LA_NOTIFICACIÓN_DE_APORTE = "Entrada para la notificación de aporte";
    public static final String ENTRADA_PARA_LA_CONFIRMACIÓN_DE_APORTE = "Entrada para la confirmación de aporte";
    public static final String ENTRADA_PARA_LA_AUTENTICACIÓN_DEL_USUARIO = "Entrada para la autenticación del usuario";
    public static final String DEBE_AUTENTICARSE_USANDO_AUTENTICAR_Y_ENVIAR_EL_TOKEN_RECIBIDO_VIA_HEADER_AUTHENTICATION_BEARER_TOKEN
            = "Debe autenticarse usando '/autenticar' y enviar el token recibido via Header Authentication Bearer TOKEN. Códigos de error:\n" +
            " 7- Error de autenticacion, credenciales del banco invalidas.\n" +
            " 8- Error de autenticacion, token no valido o expirado.";
    public static final String PARAMETROS_INCORRECTOS = "Parametros incorrectos. Código de error 10.";
    public static final String REGLA_DE_NEGOCIO_NO_CUMPLIDA_CONSULTA_NOTIFICACION = "Regla de negocio no cumplida. Códigos de error: \n" +
            "  1- NPE ya ha sido Pagado.\n" +
            "  2- NPE se ha Vencido.\n" +
            "  3- NPE no encontrado.\n" +
            "  4- Cliente no encontrado.\n " +
            "  5- Cliente no posee una cuenta de participe Activa.\n" +
            "  6- Ha ocurrido un error en el proceso, favor intente mas tarde.\n"+
            "  9- Cliente debe actualizar su informacion en Confia para poder realizar transacciones.\n" +
            "  11- NPE ya ha sido Notificado.\n"+
            "  12- Cliente bloqueado por acciones legales.";
    public static final String REGLA_DE_NEGOCIO_NO_CUMPLIDA_CONFIRMACION = "Regla de negocio no cumplida. Códigos de error: \n" +
            "  13- No existe una notificación de aporte en estado Ingresada con este correlativo.";
}
