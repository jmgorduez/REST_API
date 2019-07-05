package com.supercon.service.abstractions;

import java.util.function.Function;

public interface IDiscountManager {
    Function<Double, Double> getDiscountStrategy(String codeProduct);
}
