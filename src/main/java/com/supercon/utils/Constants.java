package com.supercon.utils;

import com.supercon.model.Product;
import com.supercon.service.builders.ProductBuilder;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String EMPTY_STRING = "";

    public static final String V1_PRODUCTS = "/v1/products";
    public static final String V1_PRODUCTS_CODE = "/v1/products/{code}";
    public static final String V1_SHOPPING_CART_CUSTOMER_NAME = "/v1/shopping-cart/{customerName}";
    public static final String V1_SHOPPING_CART_ADD_PRODUCT = "/v1/shopping-cart/add-product/{codeProduct}";
    public static final String V1_SHOPPING_CART_REMOVE_PRODUCT = "/v1/shopping-cart/remove-product/{codeProduct}";

    public static final String CODE = "{code}";

    public static final Double _14_99 = 14.99;
    public static final Double _24_99 = 24.99;

    public static final List<Product> ALL_PRODUCTS = Arrays.asList(
            new ProductBuilder()
                    .setProductCode("CHAIR_RED")
                    .setName("Red plastic chair")
                    .setPrice(_24_99).build(),
            new ProductBuilder()
                    .setProductCode("DIS_10-CHAIR_BLUE")
                    .setName("Blue plastic chair")
                    .setPrice(_24_99)
                    .setDiscountStrategy(Constants::discount10Percent).build(),
            new ProductBuilder()
                    .setProductCode("CHAIR_WHITE")
                    .setName("White plastic chair")
                    .setPrice(_24_99).build(),
            new ProductBuilder()
                    .setProductCode("STOOL_WHITE")
                    .setName("White plastic footstool")
                    .setPrice(_14_99).build(),
            new ProductBuilder()
                    .setProductCode("DIS_15-STOOL_GREEN")
                    .setName("Green plastic footstool")
                    .setPrice(_14_99)
                    .setDiscountStrategy(Constants::discount15Percent).build(),
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
        return price - 0.1 * price;
    }

    public static Integer priceDividedBy5(Double price) {
        return new Double(price / 5).intValue();
    }

    public static Integer priceDividedBy10(Double price) {
        return new Double(price / 10).intValue();
    }

    private static Double discount15Percent(Double price) {
        return price - 0.15 * price;
    }

    public static final double _0 = 0;
}
