package com.supercon.model;

import java.util.List;

public class Order extends ShoppingCart {
    public Order(Customer customer, List<Product> products) {
        super(customer, products, "ORDER_PLACED");
    }

}
