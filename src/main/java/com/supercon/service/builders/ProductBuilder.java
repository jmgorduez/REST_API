package com.supercon.service.builders;

import com.supercon.model.Product;
import com.supercon.service.builders.abstractions.IProductBuilder;

import java.util.function.Function;

import static com.supercon.utils.Constants._0;

public class ProductBuilder implements IProductBuilder {

    private String productCode;
    private String name;
    private double price;
    private Function<Double, Double> discountStrategy;
    private Function<Double, Integer> loyaltyPointsStrategy;

    public ProductBuilder(String productCode, String name) {
        this.productCode = productCode;
        this.name = name;
        this.price = _0;
        this.discountStrategy = Double::doubleValue;
        this.loyaltyPointsStrategy = Double::intValue;
    }

    @Override
    public ProductBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public ProductBuilder setLoyaltyPointsStrategy(Function<Double, Integer> loyaltyPointsStrategy) {
        this.loyaltyPointsStrategy = loyaltyPointsStrategy;
        return this;
    }

    @Override
    public ProductBuilder setDiscountStrategy(Function<Double, Double> discountStrategy) {
        this.discountStrategy = discountStrategy;
        return this;
    }

    @Override
    public String getProductCode() {
        return productCode;
    }

    @Override
    public String getName() {
        return name;
    }

    public Double getFinalPrice() {
        return discountStrategy.apply(price);
    }

    @Override
    public Integer getLoyaltyPointsEarned() {
        return loyaltyPointsStrategy.apply(price);
    }

    @Override
    public Product build() {
        return new Product(this);
    }
}