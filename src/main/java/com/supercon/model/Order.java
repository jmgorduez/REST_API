package com.supercon.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public final class Order implements Serializable {

    private final Customer customer;
    private final List<Product> products;
    private final double totalPrice;

    public Order(Customer customer, double totalPrice, List<Product> products) {
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
