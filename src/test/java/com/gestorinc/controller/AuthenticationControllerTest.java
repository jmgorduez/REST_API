package com.gestorinc.controller;

import com.gestorinc.controller.model.ClientQueryRestControllerResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
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
import org.springframework.web.context.WebApplicationContext;

import static com.gestorinc.utils.Constants.*;
import static com.gestorinc.utils.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class AuthenticationControllerTest extends AbstractControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void givenNoToken_whenGetSecureRequest_thenUnauthorized_log() throws Exception {

        RequestBuilder requestBuilder = post(V1_CONSULTAR_CLIENTE)
                .content(OBJECT_MAPPER.writeValueAsBytes(CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST))
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getStatus())
                .isEqualTo(UNAUTHORIZED.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_8_RESPONSE);

        assertThat(interfaceLogRepository.findOne(1l))
                .isEqualToIgnoringGivenFields(LOG_INTERFACE_CLIENT_QUERY_ID_12345678910_ANONYMOUS_ER_8_1,
                        FECHA_HORA_REGISTRO);
    }

    @Test
    public void givenValidUserAndPassword_whenGetSecureRequest_thenOk() throws Exception {

        String accessToken = obtainAccessToken(BANCO1, BANCO1);
        RequestBuilder requestBuilder = post(V1_CONSULTAR_CLIENTE)
                .header(AUTHORIZATION, BEARER_.concat(accessToken))
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

    @Test
    public void givenEmptyToken_whenGetSecureRequest_thenUnauthorized() throws Exception {

        RequestBuilder requestBuilder = post(V1_CONSULTAR_CLIENTE)
                .content(OBJECT_MAPPER
                        .writeValueAsBytes(CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST))
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getStatus())
                .isEqualTo(UNAUTHORIZED.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_8_RESPONSE);
    }

    @Test
    public void givenInvalidToken_whenGetSecureRequest_thenUnauthorized_log() throws Exception {

        String accessToken = obtainAccessToken(BANCO1, BANCO1);
        RequestBuilder requestBuilder = post(V1_CONSULTAR_CLIENTE)
                .header(AUTHORIZATION, BEARER_.concat(accessToken.concat(_12345678910)))
                .content(OBJECT_MAPPER
                        .writeValueAsBytes(CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST))
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getStatus())
                .isEqualTo(UNAUTHORIZED.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_8_RESPONSE);
        assertThat(interfaceLogRepository.findOne(1l))
                .isEqualToIgnoringGivenFields(LOG_INTERFACE_CLIENT_QUERY_ID_12345678910_ANONYMOUS_ER_8_1,
                        FECHA_HORA_REGISTRO);
    }

    @Test
    public void givenInvalidCredentials_whenGetSecureRequest_thenUnauthorized_log() throws Exception {

        MvcResult result = login(CLIENT_CREDENTIALS, BANCO1, BANCO1.concat(_12345678910));

        assertThat(result.getResponse().getStatus())
                .isEqualTo(UNAUTHORIZED.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_7_RESPONSE);
        assertThat(interfaceLogRepository.findOne(1l))
                .isEqualToIgnoringGivenFields(LOG_INTERFACE_LOGIN_INVALID_CREDENTIALS_ER_7_1,
                        FECHA_HORA_REGISTRO);
    }

    @Test
    public void givenValidCredentialsWithoutValidGrantType_whenGetSecureRequest_thenBadRequest_log() throws Exception {

        MvcResult result = login(BLANK_SPACE, BANCO1, BANCO1);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(BAD_REQUEST.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_10_RESPONSE);
        assertThat(interfaceLogRepository.findOne(1l))
                .isEqualToIgnoringGivenFields(LOG_INTERFACE_LOGIN_INVALID_CREDENTIALS_ER_10_1,
                        FECHA_HORA_REGISTRO);
    }

    private String obtainAccessToken(String username, String password) throws Exception {

        MvcResult result = login(CLIENT_CREDENTIALS, username, password);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());

        String resultString = result.getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get(ACCESS_TOKEN).toString();
    }

    private MvcResult login(String grantType, String username, String password) throws Exception {
        return mockMvc.perform(post(AUTENTICAR)
                .param(GRANT_TYPE, grantType)
                .param(CLIENT_ID, username)
                .param(CLIENT_SECRET, password)
                .contentType(APPLICATION_FORM_URLENCODED)
                .accept(APPLICATION_JSON_UTF8)).andReturn();
    }
}