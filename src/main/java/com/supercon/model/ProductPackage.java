package com.supercon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ProductPackage extends Product {

    private final List<Product> elements;

    public ProductPackage(@JsonProperty("price") double price,
                          @JsonProperty("productCode") String productCode,
                          @JsonProperty("name") String name,
                          @JsonProperty("loyaltyPointsEarned") Integer loyaltyPointsEarned,
                          @JsonProperty("elements") List<Product> elements) {
        super(price, productCode, name, loyaltyPointsEarned);
        this.elements = new ArrayList<>(elements);
    }

    public List<Product> getElements(){
        return elements;
    }
}
