package com.supercon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public final class Order {

    private final Customer customer;
    private final List<Product> products;
    private final double totalPrice;

    public Order(@JsonProperty("customer") Customer customer,
                 @JsonProperty("totalPrice") double totalPrice,
                 @JsonProperty("products") List<Product> products) {
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.products = new ArrayList<>(products);
    }

    public Double getTotalPrice(){
        return totalPrice;
    }

    public Customer getCustomer(){
        return customer;
    }

    public List<Product> getProducts(){
        return new ArrayList<>(products);
    }
}
