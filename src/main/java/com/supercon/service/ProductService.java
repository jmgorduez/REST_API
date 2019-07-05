package com.supercon.service;

import com.supercon.model.Product;
import com.supercon.service.abstractions.IDiscountManager;
import com.supercon.service.abstractions.IProductService;
import com.supercon.service.builders.ProductBuilder;
import com.supercon.service.builders.abstractions.IProductBuilder;
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
        return productBuilder.setProductCode(product.getProductCode())
                .setName(product.getName())
                .setPrice(product.getPrice())
                .setDiscountStrategy(discountManager.getDiscountStrategy(product.getProductCode()))
                .build();
    }

    @Override
    public List<String> getProductCodes() {
        return products.stream()
                .map(Product::getProductCode)
                .collect(Collectors.toList());
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
