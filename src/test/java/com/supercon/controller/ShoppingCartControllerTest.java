package com.supercon.controller;

import com.supercon.model.Customer;
import com.supercon.model.Order;
import com.supercon.service.ProductService;
import com.supercon.service.abstractions.IProductService;
import com.supercon.service.builders.ShoppingCart;
import com.supercon.service.builders.abstractions.IShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static com.supercon.utils.DataTestGenerator.*;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class ShoppingCartControllerTest {

    private MockMvc mockMvc;

    private IShoppingCart shoppingCart;
    private IProductService productService;

    @BeforeEach
    void setUp() {
        shoppingCart = mock(ShoppingCart.class);
        productService = mock(ProductService.class);
        mockMvc = standaloneSetup(new ShoppingCartController(shoppingCart, productService)).build();
    }

    @Test
    void createShoppingCart() throws Exception {
        Order order = new ShoppingCart()
                .setCustomer(new Customer(JOHN)).checkout();
        when(shoppingCart.checkout())
                .thenReturn(order);

        RequestBuilder requestBuilder = get(V1_SHOPPING_CART_JOHN)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(ORDER_JOHN_JSON);
    }

    @Test
    void addProductToShoppingCart() throws Exception {
        Order order = new ShoppingCart()
                .setCustomer(new Customer(JOHN))
                .addProduct(PRODUCT_01_OBJECT).checkout();
        when(shoppingCart.checkout())
                .thenReturn(order);
        when(productService.getProduct(any()))
                .thenReturn(ofNullable(PRODUCT_01_OBJECT));

        RequestBuilder requestBuilder = get(V1_SHOPPING_CART_ADD_PRODUCT_PROD_01)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(ORDER_JOHN_PROD_01_JSON);
    }

    @Test
    void removeProductToShoppingCart() throws Exception {
        when(shoppingCart.checkout())
                .thenReturn(new ShoppingCart()
                        .setCustomer(new Customer(JOHN))
                        .addProduct(PRODUCT_02_OBJECT).checkout());
        when(productService.getProduct(PROD_01))
                .thenReturn(ofNullable(PRODUCT_01_OBJECT));
        when(productService.getProduct(PROD_02))
                .thenReturn(ofNullable(PRODUCT_02_OBJECT));

        RequestBuilder requestBuilder = get(V1_SHOPPING_CART_ADD_PRODUCT_PROD_02)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        requestBuilder = get(V1_SHOPPING_CART_REMOVE_PRODUCT_PROD_01)
                .accept(MediaType.APPLICATION_JSON);
        result = mockMvc.perform(requestBuilder).andReturn();


        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(ORDER_JOHN_PROD_02_JSON);
    }
}