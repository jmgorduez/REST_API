package com.supercon.service;

import com.supercon.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.function.Function;

import static com.supercon.utils.DataTestGenerator.PROD_01;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DiscountManagerTest {

    private DiscountManager discountManagerUnderTest;

    @BeforeEach
    void setUp() {
        HashMap<String, Function<Double, Double>> discountsByProduct = new HashMap<>();
        discountsByProduct.put(PROD_01, Constants::discount10Percent);
        discountManagerUnderTest = new DiscountManager(discountsByProduct);
    }

    @Test
    void getDiscountStrategy() {
        Function<Double,Double> discount10Percent = Constants::discount10Percent;
        assertThat(discountManagerUnderTest.getDiscountStrategy(PROD_01))
                .isEqualToComparingFieldByFieldRecursively(discount10Percent);
    }
}