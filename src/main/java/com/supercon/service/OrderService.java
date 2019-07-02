package com.supercon.service;

import com.supercon.model.Customer;
import com.supercon.model.Product;
import com.supercon.service.builders.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public void showConfirmation(Customer customer, List<Product> products, double totalPrice, int loyaltyPointsEarned) {
        //show confirmation
        //do some calculations and formatting on the shopping cart data and ask user for confirmation
        //after confirmation redirect to place order
    }

    public ShoppingCart placeOrder(Customer customer, List<Product> products) {
        //place order
        return new ShoppingCart(customer);
    }

}
