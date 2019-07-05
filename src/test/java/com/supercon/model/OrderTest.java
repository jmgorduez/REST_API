package com.supercon.model;

import com.supercon.service.builders.ShoppingCart;
import com.supercon.service.builders.abstractions.IShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderTest {

    private Order orderUnderTest;
    private IShoppingCart shoppingCart;

    @BeforeEach
    void setUp() {
        this.shoppingCart = mock(ShoppingCart.class);
        when(shoppingCart.getCustomer())
                .thenReturn(new Customer(JOHN));
        when(shoppingCart.totalPrice())
                .thenReturn(_1_50);
        when(shoppingCart.getProducts())
                .thenReturn(Arrays.asList(PRODUCT_01_OBJECT, PRODUCT_02_OBJECT));
        orderUnderTest = new Order(shoppingCart);
    }

    @Test
    void getTotalPrice() {
        assertThat(orderUnderTest.getTotalPrice())
                .isEqualTo(_1_50);
    }

    @Test
    void getCustomer() {
        assertThat(orderUnderTest.getCustomer())
                .isEqualToComparingFieldByField(new Customer(JOHN));
    }

    @Test
    void getProducts() {
        assertThat(orderUnderTest.getProducts())
                .contains(PRODUCT_01_OBJECT, PRODUCT_02_OBJECT);
    }
}