package com.supercon.utils;

import com.supercon.model.Product;
import com.supercon.service.builders.ProductBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String EMPTY_STRING = "";

    public static final String V1_PRODUCTS = "/v1/products";
    public static final String V1_PRODUCTS_CODE = "/v1/products/{code}";
    public static final String V1_PRODUCTS_SET_CODE = "/v1/products/set-code";
    public static final String V1_PRODUCTS_SET_NAME = "/v1/products/set-name";
    public static final String V1_PRODUCTS_ADD_ELEMENT = "/v1/products/add-element";
    public static final String V1_SHOPPING_CART_CREATE = "/v1/shopping-cart/create";
    public static final String V1_SHOPPING_CART_ADD_PRODUCT = "/v1/shopping-cart/add-product";
    public static final String V1_SHOPPING_CART_REMOVE_PRODUCT = "/v1/shopping-cart/remove-product";
    public static final String V1_SHOPPING_CART = "/v1/shopping-cart";
    public static final String V1_DISCOUNT_MANAGER_APPLY = "/v1/discount-manager/apply";
    public static final String V1_DISCOUNT_MANAGER_PLAIN = "/v1/discount-manager/plain";

    public static final Double _0_1 = 0.1;
    public static final Double _0_15 = 0.15;
    public static final Double _14_99 = 14.99;
    public static final Double _20 = 20D;
    public static final Double _24_99 = 24.99;

    public static final String DIS_15_STOOL_GREEN = "DIS_15-STOOL_GREEN";
    public static final String DIS_10_CHAIR_BLUE = "DIS_10-CHAIR_BLUE";

    public static final List<Product> ALL_PRODUCTS = Arrays.asList(
            new ProductBuilder()
                    .setProductCode("CHAIR_RED")
                    .setName("Red plastic chair")
                    .setPrice(_24_99).build(),
            new ProductBuilder()
                    .setProductCode(DIS_10_CHAIR_BLUE)
                    .setName("Blue plastic chair")
                    .setPrice(_24_99).build(),
            new ProductBuilder()
                    .setProductCode("CHAIR_WHITE")
                    .setName("White plastic chair")
                    .setPrice(_24_99).build(),
            new ProductBuilder()
                    .setProductCode("STOOL_WHITE")
                    .setName("White plastic footstool")
                    .setPrice(_14_99).build(),
            new ProductBuilder()
                    .setProductCode(DIS_15_STOOL_GREEN)
                    .setName("Green plastic footstool")
                    .setPrice(_14_99).build(),
            new ProductBuilder()
                    .setProductCode("COMP_DESK")
                    .setName("Wooden computer desk")
                    .setPrice(74.99).build(),
            new ProductBuilder()
                    .setProductCode("COMP_CHAIR")
                    .setName("Computer swivel chair")
                    .setPrice(129.99).build(),
            new ProductBuilder()
                    .setProductCode("BOARD_TABLE")
                    .setName("12-person boardroom table")
                    .setPrice(249.99).build(),
            new ProductBuilder()
                    .setProductCode("BOARD_CHAIR")
                    .setName("Boardroom chair")
                    .setPrice(99.99).build()
    );

    public static Double discount10Percent(Double price) {
        BigDecimal formatNumber = new BigDecimal(price - _0_1 * price);
        return formatNumber.setScale(_2, RoundingMode.HALF_EVEN).doubleValue();
    }

    public static Double discount15Percent(Double price) {
        BigDecimal formatNumber = new BigDecimal(price - _0_15 * price);
        return formatNumber.setScale(_2, RoundingMode.HALF_EVEN).doubleValue();
    }

    public static Double discount20Dollars(Double price) {
        BigDecimal formatNumber = new BigDecimal(price - _20);
        return formatNumber.setScale(_2, RoundingMode.HALF_EVEN).doubleValue();
    }

    public static Integer priceDividedBy5(Double price) {
        return new Double(price / 5).intValue();
    }

    public static Integer priceDividedBy10(Double price) {
        return new Double(price / 10).intValue();
    }

    public static final int _0 = 0;
    public static final int _1 = 1;
    public static final Integer _2 = 2;
}
