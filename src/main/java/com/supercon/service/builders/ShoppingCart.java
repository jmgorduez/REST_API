package com.supercon.service.builders;

import com.supercon.model.Customer;
import com.supercon.model.Order;
import com.supercon.model.Product;
import com.supercon.service.builders.abstractions.IProductBuilder;
import com.supercon.service.builders.abstractions.IShoppingCart;
import com.supercon.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.supercon.utils.Constants.EMPTY_STRING;

@Service
public class ShoppingCart implements IShoppingCart {

    private Customer customer;
    private final List<Product> products;
    private Function<Double, Double> discountStrategy;

    public ShoppingCart() {
        this.customer = new Customer(EMPTY_STRING);
        products = new ArrayList<>();
        this.discountStrategy = Double::doubleValue;
    }

    @Override
    public IShoppingCart setCustomer(Customer customer) {
        this.customer = customer;
        return this;
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
    public IShoppingCart setDiscountStrategy(Function<Double, Double> discountStrategy) {
        this.discountStrategy = discountStrategy;
        return this;
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
        Double price = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        return discountStrategy.apply(price);
    }

    @Override
    public Integer loyaltyPointsEarned() {
        return products.stream()
                .mapToInt(Product::getLoyaltyPointsEarned)
                .sum();
    }
}
