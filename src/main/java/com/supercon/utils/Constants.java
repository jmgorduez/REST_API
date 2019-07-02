package com.supercon.utils;

import com.supercon.model.Product;
import com.supercon.service.builders.ProductBuilder;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String V1_PRODUCTS = "/v1/products";

    public static final Double _14_99 = 14.99;
    public static final Double _24_99 = 24.99;

    public static final List<Product> ALL_PRODUCTS = Arrays.asList(
            new ProductBuilder("CHAIR_RED", "Red plastic chair")
                    .setPrice(_24_99).build(),
            new ProductBuilder("DIS_10-CHAIR_BLUE", "Blue plastic chair")
                    .setPrice(_24_99)
                    .setDiscountStrategy(Constants::discount10Percent).build(),
            new ProductBuilder("CHAIR_WHITE", "White plastic chair")
                    .setPrice(_24_99).build(),
            new ProductBuilder("STOOL_WHITE", "White plastic footstool")
                    .setPrice(_14_99).build(),
            new ProductBuilder("DIS_15-STOOL_GREEN", "Green plastic footstool")
                    .setPrice(_14_99)
                    .setDiscountStrategy(Constants::discount15Percent).build(),
            new ProductBuilder("COMP_DESK", "Wooden computer desk")
                    .setPrice(74.99).build(),
            new ProductBuilder("COMP_CHAIR", "Computer swivel chair")
                    .setPrice(129.99).build(),
            new ProductBuilder("BOARD_TABLE", "12-person boardroom table")
                    .setPrice(249.99).build(),
            new ProductBuilder("BOARD_CHAIR", "Boardroom chair")
                    .setPrice(99.99).build()
    );

    public static Double discount10Percent(Double price) {
        return price - 0.1 * price;
    }

    public static Integer priceDividedBy5(Double price) {
        return new Double(price/5).intValue();
    }

    public static Integer priceDividedBy10(Double price) {
        return new Double(price/10).intValue();
    }

    private static Double discount15Percent(Double price) {
        return price - 0.15 * price;
    }

    public static final double _0 = 0;
}
