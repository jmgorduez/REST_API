package com.gestorinc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gestorinc.controller.model.*;
import com.gestorinc.exception.enums.Error;
import com.gestorinc.repository.entity.*;
import com.gestorinc.repository.entity.enums.EnumEstadoParticipe;
import com.gestorinc.repository.entity.enums.EnumSiNo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.gestorinc.exception.enums.Error.*;
import static com.gestorinc.repository.entity.enums.EnumEstadoIntencionAporte.*;
import static com.gestorinc.repository.entity.enums.EnumEstadoParticipe.A;
import static com.gestorinc.repository.entity.enums.EnumTipoCliente.PAR;
import static com.gestorinc.utils.Constants.*;

public class TestUtil {

    public static final String _11111111111111111111111111111111111 = "11111111111111111111111111111111111";
    public static final String _11111111111111111111111111111111112 = "11111111111111111111111111111111112";
    public static final String _11111111111111111111111111111111113 = "11111111111111111111111111111111113";
    public static final String _11111111111111111111111111111111114 = "11111111111111111111111111111111114";
    public static final String _11111111111111111111111111111111115 = "11111111111111111111111111111111115";
    public static final String _11111111111111111111111111111111116 = "11111111111111111111111111111111116";

    public static final String JUAN_MANUEL_GARCIA = "Juan Manuel Garcia";
    public static final String JORGE_MANUEL_GARCIA = "Jorge Manuel Garcia";
    public static final String FONDO_APV01_APV0000000001 = "Fondo APV01 APV0000000001";
    public static final String FONDO_APV01 = "Fondo APV01";
    public static final String APV01 = "APV01";
    public static final String COD_EMPRESA = "1";
    public static final String BANCO1 = "BANCO1";
    public static final String BEARER_ = "Bearer ";

    public static final String APV0000000001 = "APV0000000001";
    public static final String _____0001 = _____.concat("0001");
    public static final String APV0000000002 = "APV0000000002";
    public static final String APV0000000003 = "APV0000000003";
    public static final String _____0002 = _____.concat("0002");
    public static final String APV0000000004 = "APV0000000004";

    public static final String _12345678910 = "12345678910";
    public static final String _12345678911 = "12345678911";
    public static final String _12345678912 = "12345678912";
    public static final String _12345678913 = "12345678913";

    public static final BigDecimal _1000 = new BigDecimal(1000).setScale(2);
    public static final Long _1234 = 1234l;
    public static final Long _1111 = 1111l;
    public static final Integer NUM_LICENCIA = 1;
    public static final Integer _4321 = 4321;

    public static final Persona PERSONA_1234 = new Persona(new PersonaPK(NUM_LICENCIA, _1234), JUAN_MANUEL_GARCIA, getDate("2000-03-01"), EnumSiNo.N);
    public static final Persona PERSONA_1111 = new Persona(new PersonaPK(NUM_LICENCIA, _1111), JORGE_MANUEL_GARCIA, getDate("2000-03-01"), EnumSiNo.S);

    public static final PersonaIdentificacion PERSONA_1234_DUI_ID = new PersonaIdentificacion(
            new PersonaIdentificacionPK(NUM_LICENCIA, _1234, DUI_CODE), _12345678910);
    public static final PersonaIdentificacion PERSONA_1234_CI_ID = new PersonaIdentificacion(
            new PersonaIdentificacionPK(NUM_LICENCIA, _1234, "3"), _12345678910);
    public static final PersonaIdentificacion PERSONA_1111_CI_ID = new PersonaIdentificacion(
            new PersonaIdentificacionPK(NUM_LICENCIA, _1111, "3"), _12345678912);

    public static final String USD = "USD";
    public static final IntencionAporte INTENCION_APORTE_1 = new IntencionAporte(new IntencionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 1l),
            _11111111111111111111111111111111111, _1234, _1000, USD, APV0000000001, PEN);
    public static final IntencionAporte INTENCION_APORTE_2 = new IntencionAporte(new IntencionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 2l),
            _11111111111111111111111111111111112, _1234, _1000, USD, APV0000000001, PAG);
    public static final IntencionAporte INTENCION_APORTE_3 = new IntencionAporte(new IntencionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 3l),
            _11111111111111111111111111111111113, _1234, _1000, USD, APV0000000001, VEN);
    public static final IntencionAporte INTENCION_APORTE_4 = new IntencionAporte(new IntencionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 4l),
            _11111111111111111111111111111111114, _1234, _1000, USD, APV0000000001, NTF);
    public static final IntencionAporte INTENCION_APORTE_5 = new IntencionAporte(new IntencionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 1l),
            _11111111111111111111111111111111115, _1111, _1000, USD, APV0000000004, PEN);

    public static final Producto PRODUCTO_APV01 = new Producto(new ProductoPK(NUM_LICENCIA, COD_EMPRESA, APV01), FONDO_APV01, _4321);

    public static final Cliente CLIENTE_1234_APV0000000001 = new Cliente(new ClientePK(NUM_LICENCIA, COD_EMPRESA, APV01, _1234, PAR, 1),
            A, APV0000000001);
    public static final Cliente CLIENTE_1234_APV0000000002 = new Cliente(new ClientePK(NUM_LICENCIA, COD_EMPRESA, APV01, _1234, PAR, 2),
            A, APV0000000002);
    public static final Cliente CLIENTE_1234_APV0000000003 = new Cliente(new ClientePK(NUM_LICENCIA, COD_EMPRESA, APV01, _1234, PAR, 3),
            EnumEstadoParticipe.I, APV0000000003);
    public static final Cliente CLIENTE_1111_APV0000000004 = new Cliente(new ClientePK(NUM_LICENCIA, COD_EMPRESA, APV01, _1111, PAR, 1),
            A, APV0000000004);

    public static final ClientQueryRestControllerRequest CLIENT_QUERY_NPE_11111111111111111111111111111111111_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111111);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_NPE_11111111111111111111111111111111112_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111112);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_NPE_11111111111111111111111111111111115_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111115);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_NPE_11111111111111111111111111111111116_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111116);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(ID, _12345678910);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_CLIENT_ID_12345678911_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(ID, _12345678911);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_CLIENT_ID_12345678912_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(ID, _12345678912);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_CLIENT_ID_12345678913_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(ID, _12345678913);
    public static final ClientQueryRestControllerRequest INVALID_CLIENT_QUERY_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest("XXXXXXX", _12345678913);

    public static final List<SavingFundAccountResponse> SAVINGS_ACCOUNTS_APV0000000001_APV0000000002
            = Arrays.asList(
            SavingFundAccountResponse.builder()
                    .descripcionCuenta(FONDO_.concat(APV01).concat(BLANK_SPACE)
                            .concat(_____0001))
                    .numeroCuenta(APV0000000001)
                    .codigoGLN(_4321.toString())
                    .build(),
            SavingFundAccountResponse.builder()
                    .descripcionCuenta(FONDO_.concat(APV01).concat(BLANK_SPACE)
                            .concat(_____0002))
                    .numeroCuenta(APV0000000002)
                    .codigoGLN(_4321.toString())
                    .build());

    public static final ClientQueryRestControllerResponse CLIENT_QUERY_NPE_RESPONSE_1
            = ClientQueryRestControllerResponse.builder()
            .nombre(JUAN_MANUEL_GARCIA)
            .fondo(FONDO_APV01_APV0000000001)
            .monto(_1000)
            .respuesta(OK)
            .build();
    public static final ClientQueryRestControllerResponse CLIENT_QUERY_CLIENT_ID_RESPONSE_1
            = ClientQueryRestControllerResponse.builder()
            .cuentaAPV(SAVINGS_ACCOUNTS_APV0000000001_APV0000000002)
            .respuesta(OK)
            .build();

    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_NPE_1 = getLogInterface(1l, V1_CONSULTAR_CLIENTE,
            APV0000000001,
            CLIENT_QUERY_NPE_11111111111111111111111111111111111_REST_CONTROLLER_REQUEST, CLIENT_QUERY_NPE_RESPONSE_1,
            EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_NPE);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_NPE_2 = getLogInterface(2l, V1_CONSULTAR_CLIENTE,
            APV0000000001,
            CLIENT_QUERY_NPE_11111111111111111111111111111111111_REST_CONTROLLER_REQUEST, CLIENT_QUERY_NPE_RESPONSE_1,
            EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_NPE);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_1 = getLogInterface(1l, V1_CONSULTAR_CLIENTE,
            APV0000000001.concat(BLANK_SPACE).concat(APV0000000002),
            CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST, CLIENT_QUERY_CLIENT_ID_RESPONSE_1,
            EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_ID_CLIENTE);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_2 = getLogInterface(2l, V1_CONSULTAR_CLIENTE,
            APV0000000001.concat(BLANK_SPACE).concat(APV0000000002),
            CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST, CLIENT_QUERY_CLIENT_ID_RESPONSE_1,
            EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_ID_CLIENTE);

    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_ER_4_1 = getLogErrorInterface(1l, BANCO1,
            V1_CONSULTAR_CLIENTE,
            CLIENT_QUERY_CLIENT_ID_12345678911_REST_CONTROLLER_REQUEST,
            CLIENTE_NO_ENCONTRADO_COD_4);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_ER_4_2 = getLogErrorInterface(2l, BANCO1,
            V1_CONSULTAR_CLIENTE,
            CLIENT_QUERY_CLIENT_ID_12345678911_REST_CONTROLLER_REQUEST,
            CLIENTE_NO_ENCONTRADO_COD_4);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_ER_5_2 = getLogErrorInterface(2l, BANCO1,
            V1_CONSULTAR_CLIENTE,
            CLIENT_QUERY_CLIENT_ID_12345678913_REST_CONTROLLER_REQUEST,
            CLIENTE_NO_POSEE_CUENTA_ACTIVA_COD_5);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_NPE_ER_1_1 = getLogErrorInterface(1l, BANCO1,
            V1_CONSULTAR_CLIENTE,
            CLIENT_QUERY_NPE_11111111111111111111111111111111112_REST_CONTROLLER_REQUEST,
            NPE_PAGADO_COD_1);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_ID_12345678910_ANONYMOUS_ER_8_1 = getLogErrorInterface(1l, ANONYMOUS,
            V1_CONSULTAR_CLIENTE,
            CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST,
            ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8);
    public static final LogInterfaz LOG_INTERFACE_LOGIN_INVALID_CREDENTIALS_ER_7_1 = getLogErrorInterface(1l,
            ERROR_7_RESPONSE, ERROR_DE_AUTENTICACIÓN_DE_BANCO_CREDENCIALES_NO_VALIDAS_COD_7);
    public static final LogInterfaz LOG_INTERFACE_LOGIN_INVALID_CREDENTIALS_ER_10_1 = getLogErrorInterface(1l,
            ERROR_10_RESPONSE, ERROR_EN_LOS_PARAMETROS_RECIBIDOS_COD_10);

    private static LogInterfaz getLogInterfaceBasic(long id,
                                                    String user,
                                                    String operation,
                                                    String participat,
                                                    String product,
                                                    AbstractRestControllerRequest abstractRestControllerRequest,
                                                    AbstractRestControllerResponse RestControllerResponse,
                                                    LogInterfaz.EstadoLog status,
                                                    String message) {
        try {
            return new LogInterfaz(id, user, operation, product, participat,
                    OBJECT_MAPPER.writeValueAsString(abstractRestControllerRequest),
                    OBJECT_MAPPER.writeValueAsString(RestControllerResponse),
                    status, message);
        } catch (Exception e) {
            return null;
        }
    }

    private static LogInterfaz getLogInterface(long id,
                                               String operation,
                                               String participat,
                                               AbstractRestControllerRequest abstractRestControllerRequest,
                                               AbstractRestControllerResponse RestControllerResponse,
                                               String message) {
        try {
            return new LogInterfaz(id, BANCO1, operation, APV01, participat,
                    OBJECT_MAPPER.writeValueAsString(abstractRestControllerRequest),
                    OBJECT_MAPPER.writeValueAsString(RestControllerResponse),
                    LogInterfaz.EstadoLog.OK, message);
        } catch (Exception e) {
            return null;
        }
    }

    private static LogInterfaz getLogErrorInterface(long id,
                                                    String user,
                                                    String operation,
                                                    AbstractRestControllerRequest abstractRestControllerRequest,
                                                    Error error)  {
        try {
            return new LogInterfaz(id, user, operation, null, null,
                    OBJECT_MAPPER.writeValueAsString(abstractRestControllerRequest),
                    OBJECT_MAPPER.writeValueAsString(ErrorRestControllerResponse.builder()
                            .error(error.getCode())
                            .build()),
                    LogInterfaz.EstadoLog.ER, error.getMessage());
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private static LogInterfaz getLogErrorInterface(long id, ErrorRestControllerResponse errorRestControllerResponse,
                                                    Error error) {
        try {
            return new LogInterfaz(id, ANONYMOUS, AUTENTICAR, null, null,
                    "",
                    OBJECT_MAPPER.writeValueAsString(errorRestControllerResponse),
                    LogInterfaz.EstadoLog.ER, error.getMessage());
        } catch (Exception e) {
            return null;
        }
    }

    private static Date getDate(String value) {
        try {
            return new SimpleDateFormat(YYYY_MM_DD).parse(value);
        } catch (ParseException e) {
            return null;
        }
    }
}
