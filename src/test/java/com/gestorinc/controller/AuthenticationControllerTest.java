package com.gestorinc.controller;

import com.gestorinc.controller.model.ClientQueryRestControllerResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.security.controller.model.AuthenticationRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static com.gestorinc.exception.enums.Error.ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8;
import static com.gestorinc.utils.Constants.*;
import static com.gestorinc.utils.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class AuthenticationControllerTest extends AbstractControllerTest {

    public static final String BEARER_ = "Bearer ";
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain).build();

        personRepository.save(PERSONA_1234);
        personIdentificationRepository.save(PERSONA_1234_DUI_ID);
        personIdentificationRepository.save(PERSONA_1234_CI_ID);
        productRepository.save(PRODUCTO_APV01);
        clientRepository.save(CLIENTE_1234_APV0000000001);
        clientRepository.save(CLIENTE_1234_APV0000000002);
    }

    @Test
    public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {

        RequestBuilder requestBuilder = post(V1_CONSULTAR_CLIENTE)
                .content(OBJECT_MAPPER.writeValueAsBytes(CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST))
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getStatus())
                .isEqualTo(UNAUTHORIZED.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(
                        ErrorRestControllerResponse.builder()
                                .respuesta(ER)
                                .error(ERROR_DE_AUTENTICACIÓN_DE_BANCO_TOKEN_NO_VALIDO_O_EXPIRADO_COD_8.getCode())
                                .build());
    }

    @Test
    public void givenValidUserAndPassword_whenGetSecureRequest_thenSucces() throws Exception {

        String accessToken = obtainAccessToken(BANCO1, BANCO1);
        RequestBuilder requestBuilder = post(V1_CONSULTAR_CLIENTE)
                .header(AUTHORIZATION, BEARER_ + accessToken)
                .content(OBJECT_MAPPER
                        .writeValueAsBytes(CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST))
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ClientQueryRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(CLIENT_QUERY_CLIENT_ID_RESPONSE_1);
    }

    private String obtainAccessToken(String username, String password) throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(GRANT_TYPE, PASSWORD);
        params.add(CLIENT_ID, FOO_CLIENT_ID_PASSWORD);

        ResultActions result
                = mockMvc.perform(post(AUTENTICACION)
                .content(OBJECT_MAPPER
                        .writeValueAsBytes(AuthenticationRequest.builder()
                                .username(username)
                                .password(password).build()))
                .contentType(APPLICATION_JSON_UTF8)
                .params(params)
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get(TOKEN).toString();
    }
}