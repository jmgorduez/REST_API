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
@Sql({"/schema-test.sql", "/data-test.sql"})
public class ClientQueryControllerTest_Exceptional_Condition_NPE extends AbstractControllerTest {

    @Test
    public void clientQueryByNPE_should_return_ER_1()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY,
                new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111112));

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_1_RESPONSE);
    }

    @Test
    public void clientQueryByNPE_should_return_ER_1_log()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY,
                CLIENT_QUERY_NPE_11111111111111111111111111111111112_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_1_RESPONSE);

        assertThat(interfaceLogRepository.findOne(1l))
                .isEqualToIgnoringGivenFields(LOG_INTERFACE_CLIENT_QUERY_NPE_ER_1_1,
                        FECHA_HORA_REGISTRO);
    }

    @Test
    public void clientQueryByNPE_should_return_ER_2()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY,
                new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111113));

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_2_RESPONSE);
    }

    @Test
    public void clientQueryByNPE_should_return_ER_11()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY,
                new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111114));

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_11_RESPONSE);

    }

    @Test
    public void clientQueryByNPE_should_return_ER_3()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, CLIENT_QUERY_NPE_11111111111111111111111111111111115_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_3_RESPONSE);
    }

    @Test
    public void clientQueryByNPE_should_return_ER_9()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, CLIENT_QUERY_NPE_11111111111111111111111111111111116_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_9_RESPONSE);
    }
}