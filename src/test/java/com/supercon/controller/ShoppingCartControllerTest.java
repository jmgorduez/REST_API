package com.supercon.controller;

import com.supercon.model.Customer;
import com.supercon.service.builders.ShoppingCart;
import com.supercon.service.builders.abstractions.IShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.supercon.utils.DataTestGenerator.JOHN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShoppingCartControllerTest {

    private ShoppingCartController shoppingCartControllerUnderTest;

    @BeforeEach
    void setUp() {
        IShoppingCart shoppingCart = mock(ShoppingCart.class);
        when(shoppingCart.setCustomer(new Customer(JOHN)))
                .thenReturn(new ShoppingCart().setCustomer(new Customer(JOHN)));
        shoppingCartControllerUnderTest = new ShoppingCartController(shoppingCart);
    }

    @Test
    void createShoppingCart() {
        assertThat(shoppingCartControllerUnderTest.createShoppingCart(JOHN).getBody())
                .isEqualToComparingFieldByField(new ResponseEntity<IShoppingCart>(
                        new ShoppingCart().setCustomer(new Customer(JOHN)), HttpStatus.OK).getBody());
    }
}