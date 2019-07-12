package com.supercon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Product implements Serializable {

    protected final double price;
    protected final String productCode;
    protected final String name;
    protected final Integer loyaltyPointsEarned;

    public Product(@JsonProperty("price") double price,
                   @JsonProperty("productCode") String productCode,
                   @JsonProperty("name") String name,
                   @JsonProperty("loyaltyPointsEarned") Integer loyaltyPointsEarned){

        this.price = price;
        this.productCode = productCode;
        this.name = name;
        this.loyaltyPointsEarned = loyaltyPointsEarned;
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
