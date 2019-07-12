package com.supercon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercon.model.Product;
import com.supercon.model.ProductPackage;
import com.supercon.service.ProductService;
import com.supercon.service.abstractions.IProductService;
import com.supercon.service.builders.ProductBuilder;
import com.supercon.service.builders.abstractions.IProductBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;
import java.util.List;

import static com.supercon.utils.Constants.*;
import static com.supercon.utils.DataTestGenerator.*;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

        List<Product> products = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(products)
                .isEqualTo(PRODUCTS);
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
    void createProductPackage() throws Exception {
        when(productBuilder.setProductCode(any()))
                .thenReturn(productBuilder);
        when(productBuilder.setName(any()))
                .thenReturn(productBuilder);
        when(productBuilder.addElement(any()))
                .thenReturn(productBuilder);
        Double price = PRODUCT_01_OBJECT.getPrice() + PRODUCT_02_OBJECT.getPrice();
        ProductPackage productPackageExpected = new ProductPackage(price, PROD_03, PRODUCT_03, _3,
                Arrays.asList(PRODUCT_01_OBJECT, PRODUCT_02_OBJECT));
        when(productBuilder.build())
                .thenReturn(productPackageExpected);

        RequestBuilder requestBuilder = put(V1_PACKAGE_PRODUCTS_CREATE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper()
                        .writeValueAsString(
                                Arrays.asList(PRODUCT_01_OBJECT, PRODUCT_02_OBJECT)))
                .requestAttr(CODE_PRODUCT, PROD_03)
                .requestAttr(NAME, PRODUCT_03)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        verify(productBuilder, times(_1)).setProductCode(any());
        verify(productBuilder, times(_1)).setName(any());
        verify(productBuilder, times(_2)).addElement(any());

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.OK.value());

        ProductPackage currentProductPackage
                = new ObjectMapper().readValue(result.getResponse().getContentAsString(), ProductPackage.class);
        assertThat(currentProductPackage)
                .isEqualToComparingFieldByFieldRecursively(productPackageExpected);
    }
}