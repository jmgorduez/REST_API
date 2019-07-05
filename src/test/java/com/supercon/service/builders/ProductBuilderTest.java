package com.supercon.service.builders;

import com.supercon.model.Product;
import com.supercon.service.builders.abstractions.IProductBuilder;
import com.supercon.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.supercon.utils.DataTestGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

class ProductBuilderTest {

    private IProductBuilder productBuilderUnderTest;

    @BeforeEach
    void setUp() {
        productBuilderUnderTest = new ProductBuilder()
                .setProductCode(PROD_01)
                .setName(PRODUCT_01);
    }

    @Test
    void setPrice() throws Exception {
        IProductBuilder productBuilderExpected = new ProductBuilder()
                .setProductCode(PROD_01)
                .setName(PRODUCT_01)
                .setPrice(_1_50);
        assertThat(productBuilderUnderTest.setPrice(_1_50))
                .isEqualToComparingFieldByFieldRecursively(productBuilderExpected);
    }

    @Test
    void setDiscountStrategy() throws Exception {
        IProductBuilder productBuilderExpected = new ProductBuilder()
                .setProductCode(PROD_01)
                .setName(PRODUCT_01)
                .setDiscountStrategy(Constants::discount10Percent);
        assertThat(productBuilderUnderTest.setDiscountStrategy(Constants::discount10Percent))
                .isEqualToComparingFieldByFieldRecursively(productBuilderExpected);
    }

    @Test
    void setLoyaltyPointsStrategy() throws Exception {
        IProductBuilder productBuilderExpected = new ProductBuilder()
                .setProductCode(PROD_01)
                .setName(PRODUCT_01)
                .setLoyaltyPointsStrategy(Constants::priceDividedBy5);
        assertThat(productBuilderUnderTest.setLoyaltyPointsStrategy(Constants::priceDividedBy5))
                .isEqualToComparingFieldByFieldRecursively(productBuilderExpected);
    }

    @Test
    void getProductCode() throws Exception {
        assertThat(productBuilderUnderTest.getProductCode())
                .isEqualTo(PROD_01);
    }

    @Test
    void getName() throws Exception {
        assertThat(productBuilderUnderTest.getName())
                .isEqualTo(PRODUCT_01);
    }

    @Test
    void getFinalPrice() throws Exception {
        productBuilderUnderTest.setPrice(_1_50);
        assertThat(productBuilderUnderTest.getFinalPrice())
                .isEqualTo(_1_50);
        productBuilderUnderTest.setDiscountStrategy(Constants::discount10Percent);
        assertThat(productBuilderUnderTest.getFinalPrice())
                .isEqualTo(_1_35);
    }

    @Test
    void getLoyaltyPointsEarned() throws Exception {
        productBuilderUnderTest.setPrice(_45_80)
                .setLoyaltyPointsStrategy(Constants::priceDividedBy5);
        assertThat(productBuilderUnderTest.getLoyaltyPointsEarned())
                .isEqualTo(_9);

        productBuilderUnderTest.setLoyaltyPointsStrategy(Constants::priceDividedBy10);
        assertThat(productBuilderUnderTest.getLoyaltyPointsEarned())
                .isEqualTo(_4);
    }

    @Test
    void build() throws Exception {
        Product productExpected = new ProductBuilder()
                .setProductCode(PROD_01)
                .setName(PRODUCT_01)
                .setPrice(_1_35)
                .setDiscountStrategy(Constants::discount10Percent)
                .setLoyaltyPointsStrategy(Constants::priceDividedBy5)
                .build();
        productBuilderUnderTest
                .setPrice(_1_35)
                .setDiscountStrategy(Constants::discount10Percent)
                .setLoyaltyPointsStrategy(Constants::priceDividedBy5);
        assertThat(productBuilderUnderTest.build())
                .isEqualToComparingFieldByFieldRecursively(productExpected);
    }

    @Test
    void addElement() {
        assertThat(productBuilderUnderTest.addElement(PRODUCT_01_OBJECT))
                .isEqualToComparingFieldByField(new ProductBuilder()
                        .setProductCode(PROD_01)
                        .setName(PRODUCT_01)
                        .addElement(PRODUCT_01_OBJECT));
    }

    @Test
    void getInstance() {
        assertThat(productBuilderUnderTest.getInstance(PRODUCT_01_OBJECT))
                .isEqualToComparingFieldByFieldRecursively(new ProductBuilder()
                        .setProductCode(PROD_01)
                        .setName(PRODUCT_01)
                        .setPrice(_1_50));
    }
}