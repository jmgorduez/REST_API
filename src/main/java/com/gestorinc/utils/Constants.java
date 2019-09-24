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
}
