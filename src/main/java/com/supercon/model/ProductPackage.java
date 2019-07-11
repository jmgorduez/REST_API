package com.supercon.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductPackage extends Product {

    private final List<Product> elements;

    public ProductPackage(double price,
                          String productCode,
                          String name,
                          Integer loyaltyPointsEarned,
                          List<Product> elements) {
        super(price, productCode, name, loyaltyPointsEarned);
        this.elements = new ArrayList<>(elements);
    }

    public List<Product> getElements(){
        return elements;
    }
}
