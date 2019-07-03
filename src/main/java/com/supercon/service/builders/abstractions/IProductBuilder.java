package com.supercon.service.builders.abstractions;

import com.supercon.model.Product;

import java.util.function.Function;

public interface IProductBuilder {

    IProductBuilder setProductCode(String productCode);
    IProductBuilder setName(String name);
    IProductBuilder setPrice(double price);
    IProductBuilder setDiscountStrategy(Function<Double, Double> discountStrategy);
    IProductBuilder setLoyaltyPointsStrategy(Function<Double, Integer> loyaltyPointsStrategy);
    String getProductCode();
    String getName();
    Double getFinalPrice();
    Integer getLoyaltyPointsEarned();
    Product build();
}
