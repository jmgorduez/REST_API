package com.supercon.controller;

import com.supercon.service.ProductService;
import com.supercon.service.abstractions.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.supercon.utils.Constants.*;
import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class, secure = false)
@SpringBootTest
class ProductControllerTest {

    private MockMvc mockMvc;

    private IProductService productService;


    @BeforeEach
    protected void setUp() {
        productService = mock(ProductService.class);
        when(productService.getProductCodes())
                .thenReturn(PRODUCTS_CODES);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productService)).build();
    }

    @Test
    void getProducts() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(V1_PRODUCTS)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(PRODUCTS_CODES_JSON);
    }

    @Test
    void getProduct() throws Exception {
    }
}