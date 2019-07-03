package com.supercon.service.builders;

import com.supercon.model.Product;
import com.supercon.service.builders.abstractions.IProductBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.supercon.utils.Constants.EMPTY_STRING;
import static com.supercon.utils.Constants._0;

@Service
public class ProductBuilder implements IProductBuilder {

    private String productCode;
    private String name;
    private double price;
    private Function<Double, Double> discountStrategy;
    private Function<Double, Integer> loyaltyPointsStrategy;

    public ProductBuilder() {
        this.productCode = EMPTY_STRING;
        this.name = EMPTY_STRING;
        this.price = _0;
        this.discountStrategy = Double::doubleValue;
        this.loyaltyPointsStrategy = Double::intValue;
    }

    @Override
    public IProductBuilder setProductCode(String productCode) {
        this.productCode = productCode;
        return this;
    }

    @Override
    public IProductBuilder setName(String name) {
        this.name = name;
        return this;
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