package com.gestorinc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestorinc.controller.model.ConsultaClienteResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static com.gestorinc.utils.Constants.V1_CONSULTAR_CLIENTE;
import static com.gestorinc.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class AportesAPVControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(new AportesAPVController()).build();
    }

    @Test
    void consultaClienteNPE() throws Exception {
        /*when(shoppingCart.checkout())
                .thenReturn(order);*/

        RequestBuilder requestBuilder = post(V1_CONSULTAR_CLIENTE)
                .sessionAttr(TIPO_IDENTIFICADOR, NPE)
                .sessionAttr(IDENTIFICADOR, "")
                .sessionAttr(COD_BANCO, "")
                .sessionAttr(IP, "")
                .sessionAttr(TOKEN, "")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        /*verify(shoppingCart, times(_1))
                .setCustomer(any());*/

        assertThat(new ObjectMapper().readValue(result.getResponse().getContentAsString(), ConsultaClienteResponse.class))
                .isEqualToComparingFieldByFieldRecursively(null);
    }

    @AfterEach
    void clean() {
        mockMvc = null;
    }
}