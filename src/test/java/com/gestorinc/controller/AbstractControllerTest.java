package com.gestorinc.controller;

import com.gestorinc.controller.model.AbstractRestControllerRequest;
import com.gestorinc.controller.model.enums.OperationEndpoint;
import com.gestorinc.repository.*;
import com.gestorinc.service.abstractions.IInteractionLogManager;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import static com.gestorinc.utils.Constants.OBJECT_MAPPER;
import static com.gestorinc.utils.TestUtil.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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
    protected IInteractionLogManager interactionLogManager;
    @Autowired
    protected IContributionNotificationRepository contributionNotificationRepository;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(this.wac).build();
    }

    protected MvcResult executePostRestInteraction(OperationEndpoint operationEndpoint,
                                                   AbstractRestControllerRequest request) throws Exception {
        RequestBuilder requestBuilder = post(operationEndpoint.getUrl())
                .content(OBJECT_MAPPER.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .principal(new UsernamePasswordAuthenticationToken(BANCO1, BANCO1))
                .accept(MediaType.APPLICATION_JSON);
        return mockMvc.perform(requestBuilder).andReturn();
    }

    protected MvcResult executePutRestInteraction(OperationEndpoint operationEndpoint,
                                                   AbstractRestControllerRequest request) throws Exception {
        RequestBuilder requestBuilder = put(operationEndpoint.getUrl())
                .content(OBJECT_MAPPER.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .principal(new UsernamePasswordAuthenticationToken(BANCO1, BANCO1))
                .accept(MediaType.APPLICATION_JSON);
        return mockMvc.perform(requestBuilder).andReturn();
    }

    @After
    public void tearDown() throws Exception {
        mockMvc = null;
        interfaceLogRepository.deleteAll();
    }
}