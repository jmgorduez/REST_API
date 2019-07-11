package com.supercon.service.builders;

import com.supercon.model.Product;
import com.supercon.model.ProductPackage;
import com.supercon.service.builders.abstractions.IProductBuilder;
import com.supercon.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.supercon.utils.Constants.discount10Percent;
import static com.supercon.utils.Constants.priceDividedBy5;
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
    void finalPrice() throws Exception {
        productBuilderUnderTest.setPrice(_1_50);
        assertThat(productBuilderUnderTest.finalPrice())
                .isEqualTo(_1_50);
        productBuilderUnderTest.setDiscountStrategy(Constants::discount10Percent);
        assertThat(productBuilderUnderTest.finalPrice())
                .isEqualTo(_1_35);
    }

    @Test
    void loyaltyPointsEarned() throws Exception {
        productBuilderUnderTest.setPrice(_45_80)
                .setLoyaltyPointsStrategy(Constants::priceDividedBy5);
        assertThat(productBuilderUnderTest.loyaltyPointsEarned())
                .isEqualTo(_9);

        productBuilderUnderTest.setLoyaltyPointsStrategy(Constants::priceDividedBy10);
        assertThat(productBuilderUnderTest.loyaltyPointsEarned())
                .isEqualTo(_4);
    }

    @Test
    void build() throws Exception {
        Product productExpected = new Product(discount10Percent(_1_35),
                PROD_01, PRODUCT_01, priceDividedBy5(_1_35));
        productBuilderUnderTest
                .setPrice(_1_35)
                .setDiscountStrategy(Constants::discount10Percent)
                .setLoyaltyPointsStrategy(Constants::priceDividedBy5);
        assertThat(productBuilderUnderTest.build())
                .isEqualToComparingFieldByFieldRecursively(productExpected);
    }

    @Test
    void build_ProductPackage() throws Exception {
        ProductPackage productExpected
                = new ProductPackage(discount10Percent(PRODUCT_02_OBJECT.getPrice()),
                PROD_01, PRODUCT_01,
                priceDividedBy5(PRODUCT_02_OBJECT.getPrice()),
                Arrays.asList(PRODUCT_02_OBJECT));
        productBuilderUnderTest
                .setDiscountStrategy(Constants::discount10Percent)
                .setLoyaltyPointsStrategy(Constants::priceDividedBy5)
                .addElement(PRODUCT_02_OBJECT);
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
    void instance() {
        assertThat(productBuilderUnderTest.instance(PRODUCT_01_OBJECT))
                .isEqualToComparingFieldByFieldRecursively(new ProductBuilder()
                        .setProductCode(PROD_01)
                        .setName(PRODUCT_01)
                        .setPrice(_1_50));
    }
}