package com.gestorinc.controller;

import com.gestorinc.controller.model.ClientQueryRestControllerRequest;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import static com.gestorinc.controller.model.enums.OperationEndpoint.CLIENT_QUERY;
import static com.gestorinc.exception.enums.Error.*;
import static com.gestorinc.utils.Constants.*;
import static com.gestorinc.utils.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientQueryControllerTest_Exceptional_Condition_ClientId extends AbstractControllerTest {


    @Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(this.wac).build();
        personRepository.save(PERSONA_1234);
        personRepository.save(PERSONA_1111);
        personIdentificationRepository.save(PERSONA_1234_CI_ID);
        personIdentificationRepository.save(PERSONA_1234_DUI_ID);
        personIdentificationRepository.save(PERSONA_1111_CI_ID);
        productRepository.save(PRODUCTO_APV01);
        clientRepository.save(CLIENTE_1234_APV0000000003);
        clientRepository.save(CLIENTE_1111_APV0000000004);
        interfaceLogRepository.save(LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_ER_4_1);
    }

    @Test
    public void clientQueryByClientId_should_return_ER_4_personNotFound()
            throws Exception {

        MvcResult result = executeRestInteraction(CLIENT_QUERY, new ClientQueryRestControllerRequest(ID, _12345678911));

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_4_RESPONSE);
    }

    @Test
    public void clientQueryByClientId_should_return_ER_4_personNotFound_log()
            throws Exception {

        MvcResult result = executeRestInteraction(CLIENT_QUERY, new ClientQueryRestControllerRequest(ID, _12345678911));

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_4_RESPONSE);

        assertThat(interfaceLogRepository.findOne(2l))
                .isEqualToComparingFieldByFieldRecursively(LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_ER_4_2);
    }

    @Test
    public void clientQueryByClientId_should_return_ER_5()
            throws Exception {

        MvcResult result = executeRestInteraction(CLIENT_QUERY, CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_5_RESPONSE);
    }

    @Test
    public void clientQueryByClientId_should_return_ER_5_log()
            throws Exception {

        MvcResult result = executeRestInteraction(CLIENT_QUERY, CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_5_RESPONSE);

        assertThat(interfaceLogRepository.findOne(2l))
                .isEqualToComparingFieldByFieldRecursively(LOG_INTERFACE_CLIENT_QUERY_CLIENT_ID_ER_5_2);
    }

    @Test
    public void clientQueryByClientId_should_return_ER_9()
            throws Exception {

        MvcResult result = executeRestInteraction(CLIENT_QUERY, CLIENT_QUERY_CLIENT_ID_12345678912_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_9_RESPONSE);
    }
}