package com.supercon.controller;

import com.supercon.model.Customer;
import com.supercon.model.Order;
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

import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ShoppingCartController.class, secure = false)
@SpringBootTest
class ShoppingCartControllerTest {

    private MockMvc mockMvc;

    private ShoppingCart shoppingCart;

    @BeforeEach
    void setUp() {
        shoppingCart = mock(ShoppingCart.class);
        Order order = new ShoppingCart()
                .setCustomer(new Customer(JOHN)).checkout();
        when(shoppingCart.checkout())
                .thenReturn(order);
        mockMvc = MockMvcBuilders.standaloneSetup(new ShoppingCartController(shoppingCart)).build();
    }

    @Test
    void createShoppingCart() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(V1_SHOPPING_CART_JOHN)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(ORDER_JOHN_JSON);
    }
}