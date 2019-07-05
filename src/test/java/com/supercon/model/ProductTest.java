package com.supercon.model;

import com.supercon.service.builders.ProductBuilder;
import com.supercon.service.builders.abstractions.IProductBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductTest {

    private Product productUnderTest;
    private IProductBuilder productBuilder;

    @BeforeEach
    void setUp() {
        this.productBuilder = mock(ProductBuilder.class);
        when(productBuilder.getProductCode())
                .thenReturn(PROD_01);
        when(productBuilder.getName())
                .thenReturn(PRODUCT_01);
        when(productBuilder.getFinalPrice())
                .thenReturn(_45_80);
        when(productBuilder.getLoyaltyPointsEarned())
                .thenReturn(_13);
        when(productBuilder.getElements())
                .thenReturn(new ArrayList<>());
        productUnderTest = new Product(productBuilder);
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

    @Test
    void getElements() {
        assertThat(productUnderTest.getElements())
                .isEmpty();
    }
}