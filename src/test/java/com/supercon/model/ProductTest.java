package com.supercon.model;

import com.supercon.service.builders.ProductBuilder;
import com.supercon.service.builders.abstractions.IProductBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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