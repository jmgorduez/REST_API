package com.supercon.service.abstractions;

import com.supercon.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<String> getProductCodes();
    Optional<Product> getProduct(final String code);
}
