package com.supercon.service.abstractions;

import com.supercon.service.DiscountManager;
import com.supercon.utils.Constants;

import java.util.function.Function;

public interface IDiscountManager {

    Function<Double, Double> getDiscountStrategy(String codeProduct);
    Function<Double, Double> plainDiscountToProduct(String codeProduct, Function<Double, Double> discountStrategy);
}
