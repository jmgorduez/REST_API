package com.gestorinc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gestorinc.controller.model.*;
import com.gestorinc.controller.model.enums.OperationEndpoint;
import com.gestorinc.exception.enums.Error;
import com.gestorinc.repository.entity.*;
import com.gestorinc.repository.entity.enums.EnumEstadoNotificacionAporte;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.gestorinc.exception.enums.Error.*;
import static com.gestorinc.utils.Constants.*;

public class TestUtil {

    public static final String _11111111111111111111111111111111111 = "11111111111111111111111111111111111";
    public static final String _11111111111111111111111111111111112 = "11111111111111111111111111111111112";
    public static final String _11111111111111111111111111111111113 = "11111111111111111111111111111111113";
    public static final String _11111111111111111111111111111111114 = "11111111111111111111111111111111114";
    public static final String _11111111111111111111111111111111115 = "11111111111111111111111111111111115";
    public static final String _11111111111111111111111111111111116 = "11111111111111111111111111111111116";

    public static final String JUAN_MANUEL_GARCIA = "Juan Manuel Garcia";
    public static final String FONDO_APV01_APV0000000001 = "Fondo APV01 APV0000000001";
    public static final Integer NUM_LICENCIA = 1;
    public static final String COD_EMPRESA = "1";
    public static final String APV01 = "APV01";
    public static final String BANCO1 = "BANCO1";
    public static final String BEARER_ = "Bearer ";
    public static final String FECHA_HORA_REGISTRO = "fechaHoraRegistro";
    public static final String PK = "pk";
    public static final String FECHA_HORA_APORTE = "fechaHoraAporte";


    public static final String APV0000000001 = "APV0000000001";
    public static final String _____0001 = _____.concat("0001");
    public static final String APV0000000002 = "APV0000000002";
    public static final String _____0002 = _____.concat("0002");

    public static final String _12345678910 = "12345678910";
    public static final String _12345678911 = "12345678911";
    public static final String _12345678912 = "12345678912";
    public static final String _12345678913 = "12345678913";

    public static final BigDecimal _1000 = new BigDecimal(1000).setScale(2);
    public static final Integer _4321 = 4321;
    public static final Long _1234 = 1234l;


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

    public static final String _2019_02_01 = "2019-02-01";

    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111111_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(NPE)
                    .identificador(_11111111111111111111111111111111111)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .build();
    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111111_PM_3_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(NPE)
                    .identificador(_11111111111111111111111111111111111)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(3)
                    .build();
    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111112_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(NPE)
                    .identificador(_11111111111111111111111111111111112)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .build();
    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111113_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(NPE)
                    .identificador(_11111111111111111111111111111111113)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .build();
    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111114_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(NPE)
                    .identificador(_11111111111111111111111111111111114)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .build();
    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111115_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(NPE)
                    .identificador(_11111111111111111111111111111111115)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .build();
    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111116_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(NPE)
                    .identificador(_11111111111111111111111111111111116)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .build();

    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(ID)
                    .identificador(_12345678910)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .monto(_1000)
                    .cuentaAPV(APV0000000001)
                    .codigoGNL(_4321)
                    .build();
    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_CLIENT_ID_WITHOUT_AMOUNT_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(ID)
                    .identificador(_12345678910)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .monto(null)
                    .cuentaAPV(APV0000000001)
                    .codigoGNL(_4321)
                    .build();
    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_CLIENT_ID_WITHOUT_PARTICIPANT_ACCOUNT_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(ID)
                    .identificador(_12345678910)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .monto(_1000)
                    .cuentaAPV(null)
                    .codigoGNL(_4321)
                    .build();
    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_CLIENT_ID_WITHOUT_GLN_CODE_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(ID)
                    .identificador(_12345678910)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .monto(_1000)
                    .cuentaAPV(APV0000000001)
                    .codigoGNL(null)
                    .build();
    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_CLIENT_ID_UNREGISTERED_PERSON_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(ID)
                    .identificador(_12345678911)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .monto(_1000)
                    .cuentaAPV(APV0000000001)
                    .codigoGNL(_4321)
                    .build();
    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_CLIENT_ID_INACTIVE_CLIENT_ACCOUNT_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(ID)
                    .identificador(_12345678913)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .monto(_1000)
                    .cuentaAPV(APV0000000001)
                    .codigoGNL(_4321)
                    .build();
    public static final ContributionNotificationRestControllerRequest
            CONTRIBUTION_NOTIFICATION_CLIENT_ID_LOCAL_PERSON_WITHOUT_DUI_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador(ID)
                    .identificador(_12345678912)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .monto(_1000)
                    .cuentaAPV(APV0000000001)
                    .codigoGNL(_4321)
                    .build();

    public static final ContributionNotificationRestControllerRequest INVALID_CONTRIBUTION_NOTIFICATION_REST_CONTROLLER_REQUEST =
            ContributionNotificationRestControllerRequest.builder()
                    .tipoIdentificador("XXXXXXX")
                    .identificador(_12345678913)
                    .fechaAporte(getDate(_2019_02_01))
                    .medioPago(1)
                    .build();

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

    public static final ContributionNotificationRestControllerResponse CONTRIBUTION_NOTIFICATION_REST_CONTROLLER_RESPONSE_1
            = ContributionNotificationRestControllerResponse.builder()
            .correlativo(1l)
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
    public static final LogInterfaz LOG_INTERFACE_CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111111 =
            getLogInterface(1l, V1_NOTIFICAR_APORTE,
                    APV0000000001,
                    CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111111_REST_CONTROLLER_REQUEST,
                    CONTRIBUTION_NOTIFICATION_REST_CONTROLLER_RESPONSE_1,
                    EJECUCIÓN_DE_NOTIFICACION_DE_APORTE_POR_NPE);
    public static final LogInterfaz LOG_INTERFACE_CONTRIBUTION_NOTIFICATION_CLIENT_ID_12345678910 =
            getLogInterface(1l, V1_NOTIFICAR_APORTE,
                    APV0000000001,
                    CONTRIBUTION_NOTIFICATION_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST,
                    CONTRIBUTION_NOTIFICATION_REST_CONTROLLER_RESPONSE_1,
                    EJECUCIÓN_DE_NOTIFICACION_DE_APORTE_POR_IDENTIFICACION_DE_CLIENTE);

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
    public static final LogInterfaz LOG_INTERFACE_CONTRIBUTION_NOTIFICATION_NPE_ER_1_1
            = getLogErrorInterface(1l, BANCO1, V1_NOTIFICAR_APORTE,
            CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111112_REST_CONTROLLER_REQUEST,
            NPE_PAGADO_COD_1);

    public static final String USD = "USD";
    public static final NotificacionAporte CONTRIBUTION_NOTIFICATION_11111111111111111111111111111111111 = NotificacionAporte.builder()
            .pk(new NotificacionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 1l))
            .codigoPersona(_1234)
            .numeroCuenta(APV0000000001)
            .nPE(_11111111111111111111111111111111111)
            .codigoMoneda(USD)
            .estado(EnumEstadoNotificacionAporte.ING)
            .fechaHoraRegistro(Calendar.getInstance().getTime())
            .fechaHoraAporte(getDate(_2019_02_01))
            .codigoFormaPago("1")
            .monto(_1000)
            .build();
    public static final NotificacionAporte CONTRIBUTION_NOTIFICATION_12345678910 = NotificacionAporte.builder()
            .pk(new NotificacionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 1l))
            .codigoPersona(_1234)
            .numeroCuenta(APV0000000001)
            .nPE(null)
            .codigoMoneda(USD)
            .estado(EnumEstadoNotificacionAporte.ING)
            .fechaHoraRegistro(Calendar.getInstance().getTime())
            .fechaHoraAporte(getDate(_2019_02_01))
            .codigoFormaPago("1")
            .monto(_1000)
            .build();

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
                    status, message, getDate(_2019_02_01));
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
                    LogInterfaz.EstadoLog.OK, message, getDate(_2019_02_01));
        } catch (Exception e) {
            return null;
        }
    }

    private static LogInterfaz getLogErrorInterface(long id,
                                                    String user,
                                                    String operation,
                                                    AbstractRestControllerRequest abstractRestControllerRequest,
                                                    Error error) {
        try {
            return new LogInterfaz(id, user, operation, null, null,
                    OBJECT_MAPPER.writeValueAsString(abstractRestControllerRequest),
                    OBJECT_MAPPER.writeValueAsString(ErrorRestControllerResponse.builder()
                            .error(error.getCode())
                            .build()),
                    LogInterfaz.EstadoLog.ER, error.getMessage(), getDate(_2019_02_01));
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
                    LogInterfaz.EstadoLog.ER, error.getMessage(), getDate(_2019_02_01));
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDate(String value) {
        try {
            return new SimpleDateFormat(YYYY_MM_DD).parse(value);
        } catch (ParseException e) {
            return null;
        }
    }
}
