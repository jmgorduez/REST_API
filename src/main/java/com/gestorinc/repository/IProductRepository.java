package com.gestorinc.repository;

import com.gestorinc.repository.entity.Producto;
import com.gestorinc.repository.entity.ProductoPK;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Producto, ProductoPK> {
}
