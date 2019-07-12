package com.supercon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    private Order orderUnderTest;

    @BeforeEach
    void setUp() {
        orderUnderTest = new Order(new Customer(JOHN), _1_50,
                Arrays.asList(PRODUCT_01_OBJECT, PRODUCT_02_OBJECT));
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