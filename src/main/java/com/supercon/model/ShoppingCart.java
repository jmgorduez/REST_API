package com.supercon.model;

import com.supercon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Deprecated
public class ShoppingCart {

    //Product and quantity
    /*
    * TODO They should be referenced using interfaces
    * */
    private List<Product> products;
    private Customer customer;
    /*
    * TODO This field is not used, however if it is necessary it should be an enum
    * */
    private String cartState;

    @Autowired
    /*
    * TODO This set is not necessary,
    * it possible inject dependency annotating field private OrderService orderService with
    * @Autowired
    * */
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /*
     * This service can not be referenced form a model class
     */
    private OrderService orderService;

    public ShoppingCart(Customer customer, List<Product> products, String cartState) {
        this.customer = customer;
        this.products = products;
        this.cartState = cartState;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }


    /*
    TODO This logic should be in a service

        Checkout: Calculates total price and total loyalty points earned by the customer.
        Products with product code starting with DIS_10 have a 10% discount applied.
        Products with product code starting with DIS_15 have a 15% discount applied.

        Loyalty points are earned more when the product is not under any offer.
            Customer earns 1 point on every $5 purchase.
            Customer earns 1 point on every $10 spent on a product with 10% discount.
            Customer earns 1 point on every $15 spent on a product with 15% discount.
    */
    public void checkout() {
        double totalPrice = 0;

        int loyaltyPointsEarned = 0;
        for (Product product : products) {
            double discount = 0;
            if (product.getProductCode().startsWith("DIS_10")) {
                discount = (product.getPrice() * 0.1);
                loyaltyPointsEarned += (product.getPrice() / 10);
            } else if (product.getProductCode().startsWith("DIS_15")) {
                discount = (product.getPrice() * 0.15);
                loyaltyPointsEarned += (product.getPrice() / 15);
            } else {
                loyaltyPointsEarned += (product.getPrice() / 5);
            }

            totalPrice += product.getPrice() - discount;
        }

        orderService.showConfirmation(customer, products, totalPrice, loyaltyPointsEarned);
    }

}
