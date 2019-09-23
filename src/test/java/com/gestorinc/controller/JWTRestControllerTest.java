package com.gestorinc.controller;

import com.gestorinc.utils.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

import static com.gestorinc.utils.Constants.*;
import static com.gestorinc.utils.TestUtil.BANCO_1;
import static com.gestorinc.utils.TestUtil.CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class JWTRestControllerTest {

    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_ID = "client_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FOO_CLIENT_ID_PASSWORD = "fooClientIdPassword";
    public static final String SECRET = "secret";
    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String EMAIL = "email";

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
    public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
        RequestBuilder requestBuilder = post(V1_CONSULTAR_CLIENTE)
                .content(OBJECT_MAPPER.writeValueAsBytes(CLIENT_QUERY_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getStatus())
                .isEqualTo(UNAUTHORIZED.value());
    }

    private String obtainAccessToken(String username, String password) throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(GRANT_TYPE, PASSWORD);
        params.add(CLIENT_ID, FOO_CLIENT_ID_PASSWORD);
        params.add(USERNAME, username);
        params.add(PASSWORD, password);

        ResultActions result
                = mockMvc.perform(post(AUTENTICACION)
                .params(params)
                //.with(httpBasic(FOO_CLIENT_ID_PASSWORD, SECRET))
                .accept(APPLICATION_JSON_CHARSET_UTF_8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_CHARSET_UTF_8));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get(ACCESS_TOKEN).toString();
    }
}