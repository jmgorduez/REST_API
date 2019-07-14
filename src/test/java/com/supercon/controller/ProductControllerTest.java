package com.supercon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercon.model.Product;
import com.supercon.model.ProductPackage;
import com.supercon.service.ProductService;
import com.supercon.service.abstractions.IProductService;
import com.supercon.service.builders.ProductBuilder;
import com.supercon.service.builders.abstractions.IProductBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.*;

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

    private Queue<Product> productQueue;


    @BeforeEach
    protected void setUp() {
        productService = mock(ProductService.class);
        productBuilder = mock(ProductBuilder.class);

        when(productBuilder.setProductCode(any()))
                .thenReturn(productBuilder);
        when(productBuilder.setName(any()))
                .thenReturn(productBuilder);
        when(productBuilder.addElement(any()))
                .thenReturn(productBuilder);

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

        ObjectMapper mapper = new ObjectMapper();
        List<Product> products = mapper
                .readValue(result.getResponse().getContentAsString(),
                        mapper.getTypeFactory().constructCollectionType(List.class, Product.class));

        productQueue = new ArrayDeque<>(PRODUCTS);
        products.forEach(this::compare);
    }

    private void compare(Product product) {
        assertThat(product)
                .isEqualToComparingFieldByFieldRecursively(productQueue.poll());
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

        assertThat(new ObjectMapper().readValue(result.getResponse().getContentAsString(), Product.class))
                .isEqualToComparingFieldByFieldRecursively(PRODUCT_01_OBJECT);
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

        Double price = PRODUCT_01_OBJECT.getPrice() + PRODUCT_02_OBJECT.getPrice();
        ProductPackage productPackageExpected = new ProductPackage(price, PROD_03, PRODUCT_03, _3,
                Arrays.asList(PRODUCT_01_OBJECT, PRODUCT_02_OBJECT));
        when(productBuilder.build())
                .thenReturn(productPackageExpected);

        MvcResult result = executeCreateProductPackage();

        verify(productBuilder, times(_1)).setProductCode(any());
        verify(productBuilder, times(_1)).setName(any());
        verify(productBuilder, times(_2)).addElement(any());

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.CREATED.value());

        assertThat(new ObjectMapper().readValue(result.getResponse().getContentAsString(), ProductPackage.class))
                .isEqualToComparingFieldByFieldRecursively(productPackageExpected);
    }

    private MvcResult executeCreateProductPackage() throws Exception {
        RequestBuilder requestBuilder = post(V1_PACKAGE_PRODUCTS_CREATE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper()
                        .writeValueAsString(
                                Arrays.asList(PRODUCT_01_OBJECT, PRODUCT_02_OBJECT)))
                .requestAttr(CODE_PRODUCT, PROD_03)
                .requestAttr(NAME, PRODUCT_03)
                .accept(MediaType.APPLICATION_JSON);

        return mockMvc.perform(requestBuilder).andReturn();
    }

    @AfterEach
    void clean(){
        mockMvc = null;
        productService = null;
        productBuilder = null;
        productQueue = null;
    }
}