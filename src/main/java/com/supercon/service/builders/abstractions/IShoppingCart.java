package com.supercon.service.builders.abstractions;

import com.supercon.model.Customer;
import com.supercon.model.Order;
import com.supercon.model.Product;

import java.util.function.Function;

public interface IShoppingCart {
    IShoppingCart setCustomer(Customer customer);
    IShoppingCart addProduct(Product product);
    IShoppingCart removeProduct(Product product);
    IShoppingCart setDiscountStrategy(Function<Double, Double> discountStrategy);
    Order checkout();
    Double totalPrice();
    Integer loyaltyPointsEarned();
}
