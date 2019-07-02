package com.supercon.controller;

import com.supercon.service.abstractions.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IProductService productService;

    private ProductController  productControllerUnderTest;

    @BeforeEach
    void setUp() {
        productControllerUnderTest = new ProductController();
    }

    @Test
    void getProducts() {

    }

    @Test
    void getProduct() {
    }
}