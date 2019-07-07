package com.supercon.controller;

import com.supercon.service.ProductService;
import com.supercon.service.abstractions.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static com.supercon.utils.Constants.V1_PRODUCTS;
import static com.supercon.utils.DataTestGenerator.*;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class ProductControllerTest {

    private MockMvc mockMvc;

    private IProductService productService;


    @BeforeEach
    protected void setUp() {
        productService = mock(ProductService.class);

        mockMvc = standaloneSetup(new ProductController(productService)).build();
    }

    @Test
    void getProducts() throws Exception {
        when(productService.getProducts())
                .thenReturn(PRODUCTS);
        RequestBuilder requestBuilder = get(V1_PRODUCTS)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(PRODUCTS_JSON);
    }

    @Test
    void getProduct() throws Exception {
        when(productService.getProduct(any()))
                .thenReturn(ofNullable(PRODUCT_01_OBJECT));

        RequestBuilder requestBuilder = get(V1_PRODUCTS_PROD1)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(PROD1_JSON);
    }

    @Test
    void getProductNotExist() throws Exception {
        when(productService.getProduct(any()))
                .thenReturn(empty());

        RequestBuilder requestBuilder = get(V1_PRODUCTS_PRODX)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}