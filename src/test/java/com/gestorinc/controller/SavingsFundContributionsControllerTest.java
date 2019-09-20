package com.gestorinc.controller;

import com.gestorinc.controller.model.ClientQueryRequest;
import com.gestorinc.controller.model.ClientQueryResponse;
import com.gestorinc.repository.IContributionIntentionRepository;
import com.gestorinc.repository.IPersonRepository;
import com.gestorinc.repository.IProductRepository;
import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.repository.entity.IntencionAportePK;
import com.gestorinc.service.ClientQueryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static com.gestorinc.utils.Constants.*;
import static com.gestorinc.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class SavingsFundContributionsControllerTest {

    private MockMvc mockMvc;
    private IContributionIntentionRepository contributionIntentionRepository;
    private IPersonRepository personRepository;

    @BeforeEach
    void setUp() {
        contributionIntentionRepository = mock(IContributionIntentionRepository.class);
        personRepository = mock(IPersonRepository.class);
        mockMvc = standaloneSetup(
                new SavingsFundContributionsController(
                        new ClientQueryService(contributionIntentionRepository, personRepository)))
                .build();
    }

    @Test
    void clientQueryByNPE_should_return_the_client_query_response_for_the_contribution_intention_with_passed_NPE()
            throws Exception {
        when(contributionIntentionRepository.findByNPE(any()))
                .thenReturn(new IntencionAporte(new IntencionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 1l), _11111111111111111111111111111111111, _1234, APV01, _1000, APV0000000001));
        when(personRepository.findOne(any()))
                .thenReturn(PERSONA_1234);

        ClientQueryRequest clientQueryRequest = new ClientQueryRequest(NPE, _11111111111111111111111111111111111);

        RequestBuilder requestBuilder = post(V1_CONSULTAR_CLIENTE)
                .content(OBJECT_MAPPER.writeValueAsBytes(clientQueryRequest))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ClientQueryResponse.class))
                .isEqualToComparingFieldByFieldRecursively(
                        ClientQueryResponse.builder()
                                .nombre(JUAN_MANUEL_GARCIA)
                                .fondo(FONDO_APV01_APV0000000001)
                                .monto(_1000)
                                .respuesta(OK)
                                .build());
    }

    @AfterEach
    void clean() {
        mockMvc = null;
    }
}