package com.supercon.model;

import com.supercon.service.builders.abstractions.IProductBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {

    private final double price;
    private final String productCode;
    private final String name;
    private final Integer loyaltyPointsEarned;
    private final List<Product> elements;

    public Product(IProductBuilder builder) {
        this.productCode = builder.getProductCode();
        this.name = builder.getName();
        this.price = builder.getFinalPrice();
        this.loyaltyPointsEarned = builder.getLoyaltyPointsEarned();
        this.elements = builder.getElements();
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

    public List<Product> getElements(){
        return new ArrayList<>(elements);
    }
}
