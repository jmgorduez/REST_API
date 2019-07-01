package com.supercon.service;

import com.supercon.model.Customer;
import com.supercon.model.Product;
import com.supercon.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    public void checkout(Customer customer, List<Product> products) {
        ShoppingCart cart = new ShoppingCart(customer, products, "OPEN");

        cart.checkout();
    }

}
