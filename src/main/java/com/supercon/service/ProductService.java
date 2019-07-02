package com.supercon.service;

import com.supercon.model.Product;
import com.supercon.service.abstractions.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.supercon.utils.Constants.ALL_PRODUCTS;

@Service
public class ProductService implements IProductService {

    private List<Product> products;

    public ProductService() {
        this.products = ALL_PRODUCTS;
    }

    public ProductService(final List<Product> products) {
        this.products = products;
    }

    @Override
    public List<String> getProductCodes() {
        return products.stream()
                .map(Product::getProductCode)
                .collect(Collectors.toList());
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
