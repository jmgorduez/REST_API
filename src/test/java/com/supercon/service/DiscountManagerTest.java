package com.supercon.service;

import com.supercon.service.abstractions.IDiscountManager;
import com.supercon.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.function.Function;

import static com.supercon.utils.DataTestGenerator.PROD_01;
import static com.supercon.utils.DataTestGenerator.PROD_02;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void plainDiscountToProduct(){
        Function<Double,Double> discount15Percent = Constants::discount15Percent;
        assertThat(discountManagerUnderTest.plainDiscountToProduct(PROD_02, discount15Percent))
                .isEqualToComparingFieldByFieldRecursively(discount15Percent);
    }
}