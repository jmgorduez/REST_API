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
public class ClientQueryControllerTest_Exceptional_Condition_NPE extends AbstractControllerTest {

    @Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(this.wac).build();
        personRepository.save(PERSONA_1234);
        personRepository.save(PERSONA_1111);
        personIdentificationRepository.save(PERSONA_1111_CI_ID);
        contributionIntentionRepository.save(INTENCION_APORTE_2);
        contributionIntentionRepository.save(INTENCION_APORTE_3);
        contributionIntentionRepository.save(INTENCION_APORTE_4);
        contributionIntentionRepository.save(INTENCION_APORTE_5);
    }

    @Test
    public void clientQueryByNPE_should_return_ER_1()
            throws Exception {

        MvcResult result = executeRestInteraction(CLIENT_QUERY,
                new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111112));

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(
                        ErrorRestControllerResponse.builder()
                                .respuesta(ER)
                                .error(NPE_PAGADO_COD_1.getCode())
                                .build());
    }

    @Test
    public void clientQueryByNPE_should_return_ER_1_log()
            throws Exception {

        MvcResult result = executeRestInteraction(CLIENT_QUERY,
                CLIENT_QUERY_NPE_11111111111111111111111111111111112_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(
                        ErrorRestControllerResponse.builder()
                                .respuesta(ER)
                                .error(NPE_PAGADO_COD_1.getCode())
                                .build());

        assertThat(interfaceLogRepository.findOne(1l))
                .isEqualToComparingFieldByFieldRecursively(LOG_INTERFACE_CLIENT_QUERY_NPE_ER_1_1);
    }

    @Test
    public void clientQueryByNPE_should_return_ER_2()
            throws Exception {

        MvcResult result = executeRestInteraction(CLIENT_QUERY,
                new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111113));

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(
                        ErrorRestControllerResponse.builder()
                                .respuesta(ER)
                                .error(NPE_VENCIDO_COD_2.getCode())
                                .build());
    }

    @Test
    public void clientQueryByNPE_should_return_ER_11()
            throws Exception {

        MvcResult result = executeRestInteraction(CLIENT_QUERY,
                new ClientQueryRestControllerRequest(NPE, _11111111111111111111111111111111114));

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(
                        ErrorRestControllerResponse.builder()
                                .respuesta(ER)
                                .error(NPE_NOTIFICADO_COD_11.getCode())
                                .build());

    }

    @Test
    public void clientQueryByNPE_should_return_ER_3()
            throws Exception {

        MvcResult result = executeRestInteraction(CLIENT_QUERY, CLIENT_QUERY_NPE_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(
                        ErrorRestControllerResponse.builder()
                                .respuesta(ER)
                                .error(NPE_NO_ENCONTRADO_COD_3.getCode())
                                .build());
    }

    @Test
    public void clientQueryByNPE_should_return_ER_9()
            throws Exception {

        MvcResult result = executeRestInteraction(CLIENT_QUERY, CLIENT_QUERY_NPE_11111111111111111111111111111111115_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(
                        ErrorRestControllerResponse.builder()
                                .respuesta(ER)
                                .error(CLIENTE_DEBE_ACTUALIZAR_SU_INFORMACIÓN_EN_CONFIA_PARA_PODER_REALIZAR_TRANSACCIONES_COD_9.getCode())
                                .build());
    }
}