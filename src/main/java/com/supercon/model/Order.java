package com.supercon.model;

import com.supercon.service.builders.abstractions.IShoppingCart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public final class Order implements Serializable {

    private final Customer customer;
    private final List<Product> products;
    private final double totalPrice;

    public Order(IShoppingCart shoppingCart) {
        customer = shoppingCart.getCustomer();
        totalPrice = shoppingCart.totalPrice();
        products = new ArrayList<>(shoppingCart.getProducts());
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public Customer getCustomer(){
        return customer;
    }

    public List<Product> getProducts(){
        return new ArrayList<>(products);
    }
}
