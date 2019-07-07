package com.supercon.utils;

import com.supercon.model.Product;
import com.supercon.service.builders.ProductBuilder;

import java.util.Arrays;
import java.util.List;

public class DataTestGenerator {

    public static final String V1_SHOPPING_CART_JOHN = "/v1/shopping-cart/create";
    public static final String V1_PRODUCTS_PROD1 = "/v1/products/PROD1";
    public static final String V1_SHOPPING_CART_ADD_PRODUCT = "/v1/shopping-cart/add-product";
    public static final String V1_SHOPPING_CART_REMOVE_PRODUCT = "/v1/shopping-cart/remove-product";

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
    public static final double _1_67 = 1.67;
    public static final double _3_45 = 3.45;
    public static final double _3_83 = 3.83;
    public static final double _4_95 = 4.95;
    public static final double _45_80 = 45.80;

    public static final int _4 = 4;
    public static final int _9 = 9;
    public static final int _13 = 13;

    public static final Product PRODUCT_01_OBJECT = new ProductBuilder()
            .setProductCode(PROD_01)
            .setName(PRODUCT_01)
            .setPrice(_1_50).build();
    public static final Product PRODUCT_02_OBJECT = new ProductBuilder()
            .setProductCode(PROD_02)
            .setName(PRODUCT_02)
            .setPrice(_3_45).build();
    public static final Product PRODUCT_03_OBJECT = new ProductBuilder()
            .setProductCode(PROD_03)
            .setName(PRODUCT_03)
            .setPrice(_45_80)
            .setLoyaltyPointsStrategy(Constants::priceDividedBy5).build();
    public static final Product PRODUCT_04_OBJECT = new ProductBuilder()
            .setProductCode(PROD_04)
            .setName(PRODUCT_04)
            .setPrice(_45_80)
            .setLoyaltyPointsStrategy(Constants::priceDividedBy10).build();

    public static final List<Product> PRODUCTS = Arrays.asList(
            PRODUCT_01_OBJECT, PRODUCT_02_OBJECT
    );

    public static final List<String> PRODUCTS_CODES = Arrays.asList(
            PROD_01, PROD_02, PROD_03, PROD_04
    );

    public static final String PRODUCTS_JSON = "[{*price*:1.5,*productCode*:*PROD_01*,*name*:*Product 01*,*loyaltyPointsEarned*:1,*elements*:[]},{*price*:3.45,*productCode*:*PROD_02*,*name*:*Product 02*,*loyaltyPointsEarned*:3,*elements*:[]}]"
            .replace("*","\"");
    public static final String PRODUCT_WITH_DISCOUNT_JSON = "{*price*:1.35,*productCode*:*PROD_01*,*name*:*Product 01*,*loyaltyPointsEarned*:1,*elements*:[]}"
            .replace("*","\"");
    public static final String PROD1_JSON = "{*price*:1.5,*productCode*:*PROD_01*,*name*:*Product 01*,*loyaltyPointsEarned*:1,*elements*:[]}"
            .replace("*","\"");
    public static final String ORDER_JOHN_JSON = "{*customer*:{*name*:*JOHN*},*products*:[],*totalPrice*:0.0}"
            .replace("*","\"");
    public static final String ORDER_JOHN_PROD_01_JSON = "{*customer*:{*name*:*JOHN*},*products*:[{*price*:1.5,*productCode*:*PROD_01*,*name*:*Product 01*,*loyaltyPointsEarned*:1,*elements*:[]}],*totalPrice*:1.5}"
            .replace("*","\"");
    public static final String ORDER_JOHN_PROD_02_JSON = "{*customer*:{*name*:*JOHN*},*products*:[{*price*:3.45,*productCode*:*PROD_02*,*name*:*Product 02*,*loyaltyPointsEarned*:3,*elements*:[]}],*totalPrice*:3.45}"
            .replace("*","\"");

    public static final String TYPE_DISCOUNT = "typeDiscount";

}
