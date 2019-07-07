package com.supercon.service.enums;

import com.supercon.utils.Constants;

import java.util.function.Function;

public enum TypeDiscount {
    DISCOUNT_10_PERCENT(Constants::discount10Percent),
    DISCOUNT_15_PERCENT(Constants::discount15Percent),
    DISCOUNT_20_DOLLARS(Constants::discount20Dollars);

    private final Function<Double, Double> discountStrategy;

    TypeDiscount(Function<Double, Double> discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public Function<Double, Double> getDiscountStrategy() {
        return discountStrategy;
    }
}