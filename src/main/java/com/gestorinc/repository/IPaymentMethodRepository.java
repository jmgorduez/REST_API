package com.gestorinc.repository;

import com.gestorinc.repository.entity.TipoFormaPagoVista;
import com.gestorinc.repository.entity.TipoFormaPagoVistaPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPaymentMethodRepository extends CrudRepository<TipoFormaPagoVista, TipoFormaPagoVistaPK> {

    Optional<TipoFormaPagoVista> findByPk(TipoFormaPagoVistaPK tipoFormaPagoVistaPK);
}
