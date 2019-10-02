package com.gestorinc.repository;

import com.gestorinc.repository.entity.Producto;
import com.gestorinc.repository.entity.ProductoPK;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IProductRepository extends CrudRepository<Producto, ProductoPK> {

    Optional<Producto> findByGLN(Integer gln);
}
