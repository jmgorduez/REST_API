package com.gestorinc.repository;

import com.gestorinc.repository.entity.TipoFormaPagoVista;
import com.gestorinc.repository.entity.TipoFormaPagoVistaPK;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IPaymentMethodRepository extends CrudRepository<TipoFormaPagoVista, TipoFormaPagoVistaPK> {

    Optional<TipoFormaPagoVista> findByPk(TipoFormaPagoVistaPK tipoFormaPagoVistaPK);
}
