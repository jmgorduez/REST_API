package com.supercon.service;

import com.supercon.service.abstractions.IDiscountManager;
import com.supercon.utils.Constants;

import java.util.HashMap;
import java.util.function.Function;

import static com.supercon.utils.Constants.*;

public class DiscountManager implements IDiscountManager {

    private final HashMap<String, Function<Double, Double>> discountsByProduct;

    public DiscountManager(HashMap<String, Function<Double, Double>>... discountsByProduct) {
        if(existArgument(discountsByProduct)) {
            this.discountsByProduct = new HashMap<>();
            this.discountsByProduct.put(DIS_10_CHAIR_BLUE, price -> _0_1 * price);
            this.discountsByProduct.put(DIS_15_STOOL_GREEN, price -> _0_15 * price);
        }else{
            this.discountsByProduct = discountsByProduct[_0];
        }
    }

    private boolean existArgument(HashMap<String, Function<Double, Double>>[] discountsByProduct) {
        return discountsByProduct.length == _0;
    }

    @Override
    public Function<Double, Double> getDiscountStrategy(String codeProduct) {
        return discountsByProduct.getOrDefault(codeProduct, Double::doubleValue);
    }
}
