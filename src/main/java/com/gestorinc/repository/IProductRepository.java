package com.gestorinc.repository;

import com.gestorinc.repository.entity.Producto;
import com.gestorinc.repository.entity.ProductoPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends CrudRepository<Producto, ProductoPK> {

    Optional<Producto> findByGLN(Integer gln);
}
