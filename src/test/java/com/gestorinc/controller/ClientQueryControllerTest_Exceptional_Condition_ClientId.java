package com.gestorinc.controller;

import com.gestorinc.controller.model.ClientQueryRestControllerRequest;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import static com.gestorinc.controller.model.enums.OperationEndpoint.CLIENT_QUERY;
import static com.gestorinc.utils.Constants.*;
import static com.gestorinc.utils.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
@Sql({"/schema-h2.sql", "/data-h2.sql"})
public class ClientQueryControllerTest_Exceptional_Condition_ClientId extends AbstractControllerTest {

    @Test
    public void clientQueryByClientId_should_return_ER_4_personNotFound()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, new ClientQueryRestControllerRequest(ID, _12345678911));

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_4_RESPONSE);
    }

    @Test
    public void clientQueryByClientId_should_return_ER_4_personNotFound_log()
            throws Exception {

        interfaceLogRepository.save(LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_1);

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, new ClientQueryRestControllerRequest(ID, _12345678911));

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_4_RESPONSE);

        assertThat(interfaceLogRepository.findOne(2l))
                .isEqualToIgnoringGivenFields(LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_ER_4_2,
                        FECHA_HORA_REGISTRO);
    }

    @Test
    public void clientQueryByClientId_should_return_ER_5()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, CLIENT_QUERY_CLIENT_ID_12345678913_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_5_RESPONSE);
    }

    @Test
    public void clientQueryByClientId_should_return_ER_5_log()
            throws Exception {

        interfaceLogRepository.save(LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_1);

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, CLIENT_QUERY_CLIENT_ID_12345678913_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_5_RESPONSE);

        assertThat(interfaceLogRepository.findOne(2l))
                .isEqualToIgnoringGivenFields(LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_ER_5_2,
                        FECHA_HORA_REGISTRO);
    }

    @Test
    public void clientQueryByClientId_should_return_ER_9()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, CLIENT_QUERY_CLIENT_ID_12345678912_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_9_RESPONSE);
    }

    @Test
    public void clientQueryWithInvalidIdType_should_return_ER_10()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, INVALID_CLIENT_QUERY_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_10_RESPONSE);
    }
}