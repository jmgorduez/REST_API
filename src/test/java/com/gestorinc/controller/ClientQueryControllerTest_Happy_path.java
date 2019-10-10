package com.gestorinc.controller;

import com.gestorinc.controller.model.ClientQueryRestControllerResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import static com.gestorinc.controller.model.enums.OperationEndpoint.CLIENT_QUERY;
import static com.gestorinc.utils.Constants.OBJECT_MAPPER;
import static com.gestorinc.utils.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientQueryControllerTest_Happy_path extends AbstractControllerTest {

    @Test
    public void clientQueryByNPE_should_return_the_client_query_response_for_the_contribution_intention_by_NPE()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, CLIENT_QUERY_NPE_11111111111111111111111111111111111_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ClientQueryRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(CLIENT_QUERY_NPE_RESPONSE_1);
    }

    @Test
    public void clientQueryByNPE_should_return_the_client_query_response_for_the_contribution_intention_by_NPE_log1()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, CLIENT_QUERY_NPE_11111111111111111111111111111111111_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ClientQueryRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(CLIENT_QUERY_NPE_RESPONSE_1);

        assertThat(interfaceLogRepository.findOne(1l))
                .isEqualToIgnoringGivenFields(LOG_INTERFACE_CLIENT_QUERY_NPE_1,
                        FECHA_HORA_REGISTRO);

    }

    @Test
    public void clientQueryByNPE_should_return_the_client_query_response_for_the_contribution_intention_by_NPE_log2()
            throws Exception {

        interfaceLogRepository.save(LOG_INTERFACE_CLIENT_QUERY_NPE_1);

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, CLIENT_QUERY_NPE_11111111111111111111111111111111111_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ClientQueryRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(CLIENT_QUERY_NPE_RESPONSE_1);

        assertThat(interfaceLogRepository.findOne(2l))
                .isEqualToIgnoringGivenFields(LOG_INTERFACE_CLIENT_QUERY_NPE_2,
                        FECHA_HORA_REGISTRO);

    }

    @Test
    public void clientQueryByClientId_should_return_the_client_query_response_for_the_contribution_intention_by_client_id()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ClientQueryRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(CLIENT_QUERY_CLIENT_ID_RESPONSE_1);
    }


    @Test
    public void clientQueryByClientId_should_return_the_client_query_response_for_the_contribution_intention_by_client_id_log1()
            throws Exception {

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ClientQueryRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(CLIENT_QUERY_CLIENT_ID_RESPONSE_1);

        assertThat(interfaceLogRepository.findOne(1l))
                .isEqualToIgnoringGivenFields(LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_1,
                        FECHA_HORA_REGISTRO);
    }

    @Test
    public void clientQueryByClientId_should_return_the_client_query_response_for_the_contribution_intention_by_client_id_log2()
            throws Exception {

        interfaceLogRepository.save(LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_1);

        MvcResult result = executePostRestInteraction(CLIENT_QUERY, CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ClientQueryRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(CLIENT_QUERY_CLIENT_ID_RESPONSE_1);

        assertThat(interfaceLogRepository.findOne(2l))
                .isEqualToIgnoringGivenFields(LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_2,
                        FECHA_HORA_REGISTRO);
    }
}