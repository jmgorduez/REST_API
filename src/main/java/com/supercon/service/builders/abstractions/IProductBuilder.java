package com.supercon.service.builders.abstractions;

import com.supercon.model.Product;
import com.supercon.service.builders.ProductBuilder;

import java.util.List;
import java.util.function.Function;

public interface IProductBuilder {

    IProductBuilder getInstance(Product product);
    IProductBuilder setProductCode(String productCode);
    IProductBuilder setName(String name);
    IProductBuilder setPrice(double price);
    IProductBuilder setDiscountStrategy(Function<Double, Double> discountStrategy);
    IProductBuilder setLoyaltyPointsStrategy(Function<Double, Integer> loyaltyPointsStrategy);
    IProductBuilder addElement(Product product);
    String getProductCode();
    String getName();
    Double getFinalPrice();
    Integer getLoyaltyPointsEarned();
    Product build();
    List<Product> getElements();
}
