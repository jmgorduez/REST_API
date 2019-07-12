package com.supercon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    private Product productUnderTest;

    @BeforeEach
    void setUp() {
        productUnderTest = new Product(_45_80, PROD_01, PRODUCT_01, _13);
    }

    @Test
    void getPrice() {
        assertThat(productUnderTest.getPrice())
                .isEqualTo(_45_80);
    }

    @Test
    void getProductCode() {
        assertThat(productUnderTest.getProductCode())
                .isEqualTo(PROD_01);
    }

    @Test
    void getName() {
        assertThat(productUnderTest.getName())
                .isEqualTo(PRODUCT_01);
    }

    @Test
    void getLoyaltyPointsEarned() {
        assertThat(productUnderTest.getLoyaltyPointsEarned())
                .isEqualTo(_13);
    }
}