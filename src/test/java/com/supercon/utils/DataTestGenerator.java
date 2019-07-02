package com.supercon.utils;

import com.supercon.model.Product;
import com.supercon.service.builders.ProductBuilder;

import java.util.Arrays;
import java.util.List;

public class DataTestGenerator {

    public static final String JOHN = "JOHN";

    public static final String PROD_01 = "PROD_01";
    public static final String PROD_02 = "PROD_02";
    public static final String PROD_03 = "PROD_03";
    public static final String PROD_04 = "PROD_04";

    public static final String PRODUCT_01 = "Product 01";
    public static final String PRODUCT_02 = "Product 02";
    public static final String PRODUCT_03 = "Product 03";
    public static final String PRODUCT_04 = "Product 04";

    public static final double _1_35 = 1.35;
    public static final double _1_50 = 1.50;
    public static final double _3_45 = 3.45;
    public static final double _4_95 = 4.95;
    public static final double _45_80 = 45.80;

    public static final int _4 = 4;
    public static final int _9 = 9;
    public static final int _13 = 13;

    public static final Product PRODUCT_01_OBJECT = new ProductBuilder(PROD_01, PRODUCT_01)
            .setPrice(_1_50).build();
    public static final Product PRODUCT_02_OBJECT = new ProductBuilder(PROD_02, PRODUCT_02)
            .setPrice(_3_45).build();
    public static final Product PRODUCT_03_OBJECT = new ProductBuilder(PROD_03, PRODUCT_03)
            .setPrice(_45_80)
            .setLoyaltyPointsStrategy(Constants::priceDividedBy5).build();
    public static final Product PRODUCT_04_OBJECT = new ProductBuilder(PROD_04, PRODUCT_04)
            .setPrice(_45_80)
            .setLoyaltyPointsStrategy(Constants::priceDividedBy10).build();

    public static final List<Product> PRODUCTS = Arrays.asList(
            PRODUCT_01_OBJECT, PRODUCT_02_OBJECT
    );
}
