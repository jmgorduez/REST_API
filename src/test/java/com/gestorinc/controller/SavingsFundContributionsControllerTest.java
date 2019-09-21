package com.gestorinc.controller;

import com.gestorinc.controller.model.AbstractRequest;
import com.gestorinc.controller.model.ClientQueryRequest;
import com.gestorinc.controller.model.ClientQueryResponse;
import com.gestorinc.repository.IContributionIntentionRepository;
import com.gestorinc.repository.IPersonRepository;
import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.repository.entity.IntencionAportePK;
import com.gestorinc.service.ClientQueryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static com.gestorinc.utils.Constants.*;
import static com.gestorinc.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SavingsFundContributionsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private IContributionIntentionRepository contributionIntentionRepository;
    @Autowired
    private IPersonRepository personRepository;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(
                new SavingsFundContributionsController(
                        new ClientQueryService(contributionIntentionRepository, personRepository)))
                .build();
    }

    @Test
    public void clientQueryByNPE_should_return_the_client_query_response_for_the_contribution_intention_with_passed_NPE() throws Exception {

        clientQueryByNPE_dataInsert();

        MvcResult result = executeRestInteraction(V1_CONSULTAR_CLIENTE,
                new ClientQueryRequest(NPE, _11111111111111111111111111111111111));

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ClientQueryResponse.class))
                .isEqualToComparingFieldByFieldRecursively(
                        ClientQueryResponse.builder()
                                .nombre(JUAN_MANUEL_GARCIA)
                                .fondo(FONDO_APV01_APV0000000001)
                                .monto(_1000)
                                .respuesta(OK)
                                .build());

        clientQueryByNPE_dataDelete();
    }

    private MvcResult executeRestInteraction(String url, AbstractRequest request)
            throws Exception {
        RequestBuilder requestBuilder = post(url)
                .content(OBJECT_MAPPER.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        return mockMvc.perform(requestBuilder).andReturn();
    }

    private void clientQueryByNPE_dataDelete() {
        contributionIntentionRepository.delete(INTENCION_APORTE_1.getPk());
        personRepository.delete(PERSONA_1234.getPk());
    }

    private void clientQueryByNPE_dataInsert() {
        contributionIntentionRepository.save(INTENCION_APORTE_1);
        personRepository.save(PERSONA_1234);
    }

    @After
    public void tearDown() throws Exception {
        mockMvc = null;
    }
}