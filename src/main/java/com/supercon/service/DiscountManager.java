package com.supercon.service;

import com.supercon.service.abstractions.IDiscountManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.function.Function;

import static com.supercon.utils.Constants.*;

@Service
public class DiscountManager implements IDiscountManager {

    private final HashMap<String, Function<Double, Double>> discountsByProduct;

    public DiscountManager() {
        this.discountsByProduct = new HashMap<>();
        this.discountsByProduct.put(DIS_10_CHAIR_BLUE, price -> _0_1 * price);
        this.discountsByProduct.put(DIS_15_STOOL_GREEN, price -> _0_15 * price);

    }

    public DiscountManager(HashMap<String, Function<Double, Double>> discountsByProduct) {
        this.discountsByProduct = discountsByProduct;
    }

    @Override
    public Function<Double, Double> getDiscountStrategy(String codeProduct) {
        return discountsByProduct.getOrDefault(codeProduct, Double::doubleValue);
    }

    @Override
    public Integer plainDiscountToProduct(String codeProduct, Function<Double, Double> discountStrategy) {
        this.discountsByProduct.put(codeProduct, discountStrategy);
        return this.discountsByProduct.size();
    }
}
