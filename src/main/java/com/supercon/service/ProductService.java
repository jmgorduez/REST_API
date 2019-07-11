package com.supercon.service;

import com.supercon.model.Product;
import com.supercon.service.abstractions.IDiscountManager;
import com.supercon.service.abstractions.IProductService;
import com.supercon.service.builders.abstractions.IProductBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.supercon.utils.Constants.ALL_PRODUCTS;

@Service
public class ProductService implements IProductService {

    private List<Product> products;
    private IDiscountManager discountManager;
    private IProductBuilder productBuilder;

    @Autowired
    public ProductService(IDiscountManager discountManager, IProductBuilder productBuilder) {
        this(discountManager, productBuilder, ALL_PRODUCTS);
    }

    public ProductService(IDiscountManager discountManager, IProductBuilder productBuilder, final List<Product> products) {
        this.productBuilder = productBuilder;
        this.discountManager = discountManager;
        this.products = applyDiscountsToProducts(products);
    }

    private List<Product> applyDiscountsToProducts(List<Product> products){
        return products.stream()
                .map(this::applyDiscount)
                .collect(Collectors.toList());
    }

    private Product applyDiscount(Product product){
        return productBuilder
                .instance(product)
                .setDiscountStrategy(discountManager.getDiscountStrategy(product.getProductCode()))
                .build();
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Optional<Product> getProduct(final String code) {
        return products.stream()
                .filter(isEqual(code))
                .reduce(this::takeFirst);
    }

    private Product takeFirst(Product product, Product product2) {
        return product;
    }

    private Predicate<Product> isEqual(String code) {
        return product -> product.getProductCode().equals(code);
    }
}
