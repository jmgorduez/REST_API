package com.supercon.model;

import com.supercon.service.builders.abstractions.IProductBuilder;

import java.io.Serializable;

public class Product implements Serializable {

    private final double price;
    private final String productCode;
    private final String name;
    private final Integer loyaltyPointsEarned;

    public Product(IProductBuilder builder) {
        this.productCode = builder.getProductCode();
        this.name = builder.getName();
        this.price = builder.getFinalPrice();
        this.loyaltyPointsEarned = builder.getLoyaltyPointsEarned();
    }

    public Double getPrice() { return price; }

    public String getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }

    public Integer getLoyaltyPointsEarned(){
        return loyaltyPointsEarned;
    }
}
