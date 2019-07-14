package com.supercon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercon.model.Customer;
import com.supercon.model.Order;
import com.supercon.service.builders.ShoppingCart;
import com.supercon.service.builders.abstractions.IShoppingCart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import static com.supercon.utils.Constants.*;
import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
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
        Order order = new Order(new Customer(JOHN), _0, new ArrayList<>());
        when(shoppingCart.checkout())
                .thenReturn(order);

        RequestBuilder requestBuilder = post(V1_SHOPPING_CART_JOHN)
                .content(new ObjectMapper().writeValueAsString(new Customer(JOHN)))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        verify(shoppingCart, times(_1))
                .setCustomer(any());

        assertThat(new ObjectMapper().readValue(result.getResponse().getContentAsString(), Order.class))
                .isEqualToComparingFieldByFieldRecursively(order);
    }

    @Test
    void addProductToShoppingCart() throws Exception {
        Order order = new Order(new Customer(JOHN), _1_50, Arrays.asList(PRODUCT_01_OBJECT));

        when(shoppingCart.checkout())
                .thenReturn(order);

        RequestBuilder requestBuilder = put(V1_SHOPPING_CART_ADD_PRODUCT)
                .content(new ObjectMapper().writeValueAsString(PRODUCT_01_OBJECT))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        verify(shoppingCart, times(_1))
                .addProduct(any());

        assertThat(new ObjectMapper().readValue(result.getResponse().getContentAsString(), Order.class))
                .isEqualToComparingFieldByFieldRecursively(order);
    }

    @Test
    void removeProductToShoppingCart() throws Exception {

        Order order = new Order(new Customer(JOHN), _3_45, Arrays.asList(PRODUCT_02_OBJECT));

        when(shoppingCart.checkout())
                .thenReturn(order);

        addTwoProductToShoppingCart();

        MvcResult result = executeRemoveProductToShoppingCart();

        verify(shoppingCart, times(_1))
                .removeProduct(any());

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.NO_CONTENT.value());

        assertThat(new ObjectMapper().readValue(result.getResponse().getContentAsString(), Order.class))
                .isEqualToComparingFieldByFieldRecursively(order);
    }

    private MvcResult executeRemoveProductToShoppingCart() throws Exception {
        RequestBuilder requestBuilder = delete(V1_SHOPPING_CART_REMOVE_PRODUCT)
                .content(new ObjectMapper().writeValueAsString(PRODUCT_01_OBJECT))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        return mockMvc.perform(requestBuilder).andReturn();
    }

    private void addTwoProductToShoppingCart() throws Exception {
        RequestBuilder requestBuilder = put(V1_SHOPPING_CART_ADD_PRODUCT)
                .content(new ObjectMapper().writeValueAsString(PRODUCT_01_OBJECT))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andReturn();

        requestBuilder = put(V1_SHOPPING_CART_ADD_PRODUCT)
                .content(new ObjectMapper().writeValueAsString(PRODUCT_02_OBJECT))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andReturn();
    }

    @Test
    void getShoppingCart() throws Exception {
        Order order = new Order(new Customer(JOHN), _3_45, Arrays.asList(PRODUCT_02_OBJECT));
        when(shoppingCart.checkout())
                .thenReturn(order);

        RequestBuilder requestBuilder = get(V1_SHOPPING_CART)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        verify(shoppingCart, times(_1))
                .checkout();

        assertThat(new ObjectMapper().readValue(result.getResponse().getContentAsString(), Order.class))
                .isEqualToComparingFieldByFieldRecursively(order);
    }

    @AfterEach
    void clean() {
        mockMvc = null;
        shoppingCart = null;
    }
}