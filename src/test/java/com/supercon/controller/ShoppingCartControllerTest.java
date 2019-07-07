package com.supercon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercon.model.Customer;
import com.supercon.model.Order;
import com.supercon.service.ProductService;
import com.supercon.service.abstractions.IProductService;
import com.supercon.service.builders.ShoppingCart;
import com.supercon.service.builders.abstractions.IShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static com.supercon.utils.Constants.V1_SHOPPING_CART;
import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class ShoppingCartControllerTest {

    private MockMvc mockMvc;

    private IShoppingCart shoppingCart;

    @BeforeEach
    void setUp() {
        shoppingCart = mock(ShoppingCart.class);
        mockMvc = standaloneSetup(new ShoppingCartController(shoppingCart)).build();
    }

    @Test
    void createShoppingCart() throws Exception {
        Order order = new ShoppingCart()
                .setCustomer(new Customer(JOHN)).checkout();
        when(shoppingCart.checkout())
                .thenReturn(order);

        RequestBuilder requestBuilder = post(V1_SHOPPING_CART_JOHN)
                .content(new ObjectMapper().writeValueAsString(new Customer(JOHN)))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
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

        RequestBuilder requestBuilder = put(V1_SHOPPING_CART_ADD_PRODUCT)
                .content(new ObjectMapper().writeValueAsString(PRODUCT_01_OBJECT))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(ORDER_JOHN_PROD_01_JSON);
    }

    @Test
    void removeProductToShoppingCart() throws Exception {
        Order order = new ShoppingCart()
                .setCustomer(new Customer(JOHN))
                .addProduct(PRODUCT_02_OBJECT).checkout();
        when(shoppingCart.checkout())
                .thenReturn(order);

        RequestBuilder requestBuilder = put(V1_SHOPPING_CART_ADD_PRODUCT)
                .content(new ObjectMapper().writeValueAsString(PRODUCT_01_OBJECT))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        requestBuilder = put(V1_SHOPPING_CART_ADD_PRODUCT)
                .content(new ObjectMapper().writeValueAsString(PRODUCT_02_OBJECT))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        result = mockMvc.perform(requestBuilder).andReturn();

        requestBuilder = delete(V1_SHOPPING_CART_REMOVE_PRODUCT)
                .content(new ObjectMapper().writeValueAsString(PRODUCT_01_OBJECT))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        result = mockMvc.perform(requestBuilder).andReturn();


        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(ORDER_JOHN_PROD_02_JSON);
    }

    @Test
    void getShoppingCart() throws Exception {
        when(shoppingCart.checkout())
                .thenReturn(new ShoppingCart()
                        .setCustomer(new Customer(JOHN))
                        .addProduct(PRODUCT_02_OBJECT).checkout());

        RequestBuilder requestBuilder = get(V1_SHOPPING_CART)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(ORDER_JOHN_PROD_02_JSON);
    }
}