package com.gestorinc.utils;

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

    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_ID = "client_id";
    public static final String PASSWORD = "password";
    public static final String FOO_CLIENT_ID_PASSWORD = "fooClientIdPassword";
    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
    public static final String ACCESS_TOKEN = "access_token";

    public static final String _11111111111111111111111111111111111 = "11111111111111111111111111111111111";
    public static final String _11111111111111111111111111111111112 = "11111111111111111111111111111111112";
    public static final String _11111111111111111111111111111111113 = "11111111111111111111111111111111113";
    public static final String _11111111111111111111111111111111114 = "11111111111111111111111111111111114";
    public static final String _11111111111111111111111111111111115 = "11111111111111111111111111111111115";

    public static final String JUAN_MANUEL_GARCIA = "Juan Manuel Garcia";
    public static final String JORGE_MANUEL_GARCIA = "Jorge Manuel Garcia";
    public static final String FONDO_APV01_APV0000000001 = "Fondo APV01 APV0000000001";
    public static final String FONDO_APV01 = "Fondo APV01";
    public static final String APV01 = "APV01";
    public static final String COD_EMPRESA = "1";
    public static final String BANCO1 = "BANCO1";

    public static final String APV0000000001 = "APV0000000001";
    public static final String _____0001 = _____.concat("0001");
    public static final String APV0000000002 = "APV0000000002";
    public static final String APV0000000003 = "APV0000000003";
    public static final String _____0002 = _____.concat("0002");
    public static final String APV0000000004 = "APV0000000004";

    public static final String _12345678910 = "12345678910";
    public static final String _12345678911 = "12345678911";
    public static final String _12345678912 = "12345678912";

    public static final BigDecimal _1000 = new BigDecimal(1000).setScale(2);
    public static final Long _1234 = new Long(1234);
    public static final Long _1111 = new Long(1111);
    public static final Integer NUM_LICENCIA = 1;
    public static final Integer _4321 = 4321;

    public static final Persona PERSONA_1234 = new Persona(new PersonaPK(NUM_LICENCIA, _1234), JUAN_MANUEL_GARCIA, getDate("2000-03-01"), EnumSiNo.N);
    public static final Persona PERSONA_1111 = new Persona(new PersonaPK(NUM_LICENCIA, _1111), JORGE_MANUEL_GARCIA, getDate("2000-03-01"), EnumSiNo.S);
    public static final PersonaIdentificacion PERSONA_1234_DUI_ID = new PersonaIdentificacion(
            new PersonaIdentificacionPK(NUM_LICENCIA, _1234, "10"), _12345678910);
    public static final PersonaIdentificacion PERSONA_1234_CI_ID = new PersonaIdentificacion(
            new PersonaIdentificacionPK(NUM_LICENCIA, _1234, "3"), _12345678910);
    public static final PersonaIdentificacion PERSONA_1111_CI_ID = new PersonaIdentificacion(
            new PersonaIdentificacionPK(NUM_LICENCIA, _1111, "3"), _12345678912);
    public static final IntencionAporte INTENCION_APORTE_1 = new IntencionAporte(new IntencionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 1l),
            _11111111111111111111111111111111111, _1234, _1000, APV0000000001, PEN);
    public static final IntencionAporte INTENCION_APORTE_2 = new IntencionAporte(new IntencionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 2l),
            _11111111111111111111111111111111112, _1234, _1000, APV0000000001, PAG);
    public static final IntencionAporte INTENCION_APORTE_3 = new IntencionAporte(new IntencionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 3l),
            _11111111111111111111111111111111113, _1234, _1000, APV0000000001, VEN);
    public static final IntencionAporte INTENCION_APORTE_4 = new IntencionAporte(new IntencionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 4l),
            _11111111111111111111111111111111114, _1234, _1000, APV0000000001, NTF);
    public static final IntencionAporte INTENCION_APORTE_5 = new IntencionAporte(new IntencionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 1l),
            _11111111111111111111111111111111115, _1111, _1000, APV0000000004, PEN);
    public static final Producto PRODUCTO_APV01 = new Producto(new ProductoPK(NUM_LICENCIA, COD_EMPRESA, APV01), FONDO_APV01, _4321);
    public static final Cliente CLIENTE_1234_APV0000000001 = new Cliente(new ClientePK(NUM_LICENCIA, COD_EMPRESA, APV01, _1234, PAR, 1),
            A, APV0000000001);
    public static final Cliente CLIENTE_1234_APV0000000002 = new Cliente(new ClientePK(NUM_LICENCIA, COD_EMPRESA, APV01, _1234, PAR, 2),
            A, APV0000000002);
    public static final Cliente CLIENTE_1234_APV0000000003 = new Cliente(new ClientePK(NUM_LICENCIA, COD_EMPRESA, APV01, _1234, PAR, 3),
            EnumEstadoParticipe.I, APV0000000003);
    public static final Cliente CLIENTE_1111_APV0000000004 = new Cliente(new ClientePK(NUM_LICENCIA, COD_EMPRESA, APV01, _1111, PAR, 1),
            A, APV0000000004);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_NPE_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111111);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_NPE_11111111111111111111111111111111112_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111112);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_NPE_11111111111111111111111111111111115_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111115);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(ID, _12345678910);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_CLIENT_ID_12345678911_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(ID, _12345678911);
    public static final ClientQueryRestControllerRequest CLIENT_QUERY_CLIENT_ID_12345678912_REST_CONTROLLER_REQUEST =
            new ClientQueryRestControllerRequest(ID, _12345678912);
    public static final List<String> SAVINGS_ACCOUNTS_APV0000000001_APV0000000002
            = Arrays.asList(
            FONDO_.concat(APV01).concat(BLANK_SPACE)
                    .concat(_____0001).concat(BLANK_SPACE)
                    .concat(APV0000000001).concat(BLANK_SPACE)
                    .concat(_4321.toString()),
            FONDO_.concat(APV01).concat(BLANK_SPACE)
                    .concat(_____0002).concat(BLANK_SPACE)
                    .concat(APV0000000002).concat(BLANK_SPACE)
                    .concat(_4321.toString()));
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
            CLIENT_QUERY_NPE_REST_CONTROLLER_REQUEST, CLIENT_QUERY_NPE_RESPONSE_1,
            EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_NPE);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_NPE_2 = getLogInterface(2l, V1_CONSULTAR_CLIENTE,
            APV0000000001,
            CLIENT_QUERY_NPE_REST_CONTROLLER_REQUEST, CLIENT_QUERY_NPE_RESPONSE_1,
            EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_NPE);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_1 = getLogInterface(1l, V1_CONSULTAR_CLIENTE,
            APV0000000001.concat(BLANK_SPACE).concat(APV0000000002),
            CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST, CLIENT_QUERY_CLIENT_ID_RESPONSE_1,
            EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_ID_CLIENTE);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_2 = getLogInterface(2l, V1_CONSULTAR_CLIENTE,
            APV0000000001.concat(BLANK_SPACE).concat(APV0000000002),
            CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST, CLIENT_QUERY_CLIENT_ID_RESPONSE_1,
            EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_ID_CLIENTE);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_ER_4_1 = getLogErrorInterface(1l,
            V1_CONSULTAR_CLIENTE,
            CLIENT_QUERY_CLIENT_ID_12345678911_REST_CONTROLLER_REQUEST,
            CLIENTE_NO_ENCONTRADO_COD_4);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_ER_4_2 = getLogErrorInterface(2l,
            V1_CONSULTAR_CLIENTE,
            CLIENT_QUERY_CLIENT_ID_12345678911_REST_CONTROLLER_REQUEST,
            CLIENTE_NO_ENCONTRADO_COD_4);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_ER_5_2 = getLogErrorInterface(2l,
            V1_CONSULTAR_CLIENTE,
            CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST,
            CLIENTE_NO_POSEE_CUENTA_ACTIVA_COD_5);
    public static final LogInterfaz LOG_INTERFACE_CLIENT_QUERY_NPE_ER_1_1 = getLogErrorInterface(1l,
            V1_CONSULTAR_CLIENTE,
            CLIENT_QUERY_NPE_11111111111111111111111111111111112_REST_CONTROLLER_REQUEST,
            NPE_PAGADO_COD_1);

    private static LogInterfaz getLogInterface(long id,
                                               String operation,
                                               String participat,
                                               String product,
                                               AbstractRestControllerRequest abstractRestControllerRequest,
                                               AbstractRestControllerResponse abstractRestControllerResponse,
                                               LogInterfaz.EstadoLog status,
                                               String message) {
        try {
            return new LogInterfaz(id, BANCO1, operation, product, participat,
                    OBJECT_MAPPER.writeValueAsString(abstractRestControllerRequest),
                    OBJECT_MAPPER.writeValueAsString(abstractRestControllerResponse),
                    status, message);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static LogInterfaz getLogInterface(long id,
                                               String operation,
                                               String participat,
                                               AbstractRestControllerRequest abstractRestControllerRequest,
                                               AbstractRestControllerResponse abstractRestControllerResponse,
                                               String message) {
        try {
            return new LogInterfaz(id, BANCO1, operation, APV01, participat,
                    OBJECT_MAPPER.writeValueAsString(abstractRestControllerRequest),
                    OBJECT_MAPPER.writeValueAsString(abstractRestControllerResponse),
                    LogInterfaz.EstadoLog.OK, message);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static LogInterfaz getLogErrorInterface(long id,
                                                    String operation,
                                                    AbstractRestControllerRequest abstractRestControllerRequest,
                                                    Error error) {
        return getLogInterface(id, operation, null, null,
                abstractRestControllerRequest,
                new ErrorRestControllerResponse(ER, error.getCode()),
                LogInterfaz.EstadoLog.ER,
                error.getMessage());
    }

    private static Date getDate(String value) {
        try {
            return new SimpleDateFormat(YYYY_MM_DD).parse(value);
        } catch (ParseException e) {
            return null;
        }
    }
}
