package com.supercon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercon.model.Product;
import com.supercon.model.ProductPackage;
import com.supercon.service.ProductService;
import com.supercon.service.abstractions.IProductService;
import com.supercon.service.builders.ProductBuilder;
import com.supercon.service.builders.abstractions.IProductBuilder;
import com.supercon.utils.DataTestGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;

import static com.supercon.utils.Constants.*;
import static com.supercon.utils.DataTestGenerator.*;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class ProductControllerTest {

    private MockMvc mockMvc;

    private IProductService productService;
    private IProductBuilder productBuilder;


    @BeforeEach
    protected void setUp() {
        productService = mock(ProductService.class);
        productBuilder = mock(ProductBuilder.class);

        mockMvc = standaloneSetup(new ProductController(productService, productBuilder)).build();
    }

    @Test
    void getProducts() throws Exception {
        when(productService.getProducts())
                .thenReturn(PRODUCTS);
        RequestBuilder requestBuilder = get(V1_PRODUCTS)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        verify(productService, times(_1))
                .getProducts();

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

        verify(productService, times(_1))
                .getProduct(any());

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

        verify(productService, times(_1))
                .getProduct(any());

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void setCodeProduct() throws Exception {
        when(productBuilder.setProductCode(any()))
                .thenReturn(new ProductBuilder()
                        .setProductCode(PROD_01));
        when(productBuilder.build())
                .thenReturn(new ProductBuilder()
                        .setProductCode(PROD_01)
                        .build());

        RequestBuilder requestBuilder = put(V1_PRODUCTS_SET_CODE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(PROD_01)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        verify(productBuilder, times(_1))
                .setProductCode(any());

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(PROD1_JSON_0);
    }

    @Test
    void setNameProduct() throws Exception {
        when(productBuilder.setName(any()))
                .thenReturn(new ProductBuilder()
                        .setName(PRODUCT_01));
        when(productBuilder.build())
                .thenReturn(new ProductBuilder()
                        .setName(PRODUCT_01)
                        .build());

        RequestBuilder requestBuilder = put(V1_PRODUCTS_SET_NAME)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(PRODUCT_01)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        verify(productBuilder, times(_1))
                .setName(any());

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(PROD1_JSON_1);
    }

    @Test
    void addElement() throws Exception {
        when(productBuilder.addElement(any()))
                .thenReturn(new ProductBuilder()
                        .addElement(PRODUCT_02_OBJECT));
        when(productBuilder.build())
                .thenReturn( new ProductPackage(PRODUCT_02_OBJECT.getPrice(),
                        PROD_02, PRODUCT_02, _3, Arrays.asList(PRODUCT_02_OBJECT)));
        when(productService.getProduct(any()))
                .thenReturn(ofNullable(PRODUCT_02_OBJECT));

        RequestBuilder requestBuilder = put(V1_PRODUCTS_ADD_ELEMENT)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(PROD_01)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        verify(productBuilder, times(_1))
                .addElement(any());

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(PROD1_JSON_2);
    }
}