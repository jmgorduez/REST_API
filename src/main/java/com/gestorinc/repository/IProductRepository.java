package com.gestorinc.repository;

import com.gestorinc.exception.LogicBusinessException;
import com.gestorinc.repository.entity.Producto;
import com.gestorinc.repository.entity.ProductoPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.gestorinc.exception.enums.Error.PRODUCTO_CON_GLN_NO_ENCONTRADO;

@Repository
public interface IProductRepository extends CrudRepository<Producto, ProductoPK> {

    Optional<Producto> findByGLN(String gln);

    default void validateIfExistProductWithGNL(String gln){
        findByGLN(gln)
                .orElseThrow(this::productWithGLNNotFoundException);
    }

    default LogicBusinessException productWithGLNNotFoundException() {
        return new LogicBusinessException(PRODUCTO_CON_GLN_NO_ENCONTRADO);
    }
}
