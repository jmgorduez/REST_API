package com.supercon.service.builders;

import com.supercon.model.Customer;
import com.supercon.model.Order;
import com.supercon.model.Product;
import com.supercon.service.builders.abstractions.IShoppingCart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ShoppingCart implements IShoppingCart {

    public void checkout(Customer customer, List<Product> products) {
        com.supercon.model.ShoppingCart cart = new com.supercon.model.ShoppingCart(customer, products, "OPEN");

        cart.checkout();
    }

    private final Customer customer;
    private final List<Product> products;

    public ShoppingCart(Customer customer){
        this.customer = customer;
        products = new ArrayList<>();
    }

    @Override
    public IShoppingCart addProduct(Product product) {
        products.add(product);
        return this;
    }

    @Override
    public IShoppingCart removeProduct(Product product) {
        products.removeIf(isEqual(product));
        return this;
    }

    private Predicate<Product> isEqual(Product product) {
        return product1 -> product1.getProductCode().equals(product.getProductCode());
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public Order checkout() {
        return new Order(this);
    }

    @Override
    public Double totalPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public Integer loyaltyPointsEarned() {
        return products.stream()
                .mapToInt(Product::getLoyaltyPointsEarned)
                .sum();
    }
}
