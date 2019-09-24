package com.gestorinc.controller;

import com.gestorinc.controller.model.AbstractRestControllerRequest;
import com.gestorinc.controller.model.enums.OperationEndpoint;
import com.gestorinc.repository.*;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import static com.gestorinc.utils.Constants.OBJECT_MAPPER;
import static com.gestorinc.utils.TestUtil.BANCO1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public abstract class AbstractControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    protected IContributionIntentionRepository contributionIntentionRepository;
    @Autowired
    protected IPersonRepository personRepository;
    @Autowired
    protected IPersonIdentificationRepository personIdentificationRepository;
    @Autowired
    protected IProductRepository productRepository;
    @Autowired
    protected IClientRepository clientRepository;
    @Autowired
    protected IInterfaceLogRepository interfaceLogRepository;

    @Autowired
    protected WebApplicationContext wac;

    protected MvcResult executeRestInteraction(OperationEndpoint operationEndpoint,
                                               AbstractRestControllerRequest request) throws Exception {
        RequestBuilder requestBuilder = post(operationEndpoint.getUrl())
                .content(OBJECT_MAPPER.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .principal(new UsernamePasswordAuthenticationToken(BANCO1, BANCO1))
                .accept(MediaType.APPLICATION_JSON);
        return mockMvc.perform(requestBuilder).andReturn();
    }

    @After
    public void tearDown() throws Exception {
        mockMvc = null;
        personRepository.deleteAll();
        personIdentificationRepository.deleteAll();
        productRepository.deleteAll();
        clientRepository.deleteAll();
        contributionIntentionRepository.deleteAll();
        interfaceLogRepository.deleteAll();
    }
}