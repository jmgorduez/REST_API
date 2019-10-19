package com.gestorinc.repository;

import com.gestorinc.exception.LogicBusinessException;
import com.gestorinc.repository.entity.TipoFormaPagoVista;
import com.gestorinc.repository.entity.TipoFormaPagoVistaPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.gestorinc.exception.enums.Error.FORMA_PAGO_NO_EXISTE_14;

@Repository
public interface IPaymentMethodRepository extends CrudRepository<TipoFormaPagoVista, TipoFormaPagoVistaPK> {

    Optional<TipoFormaPagoVista> findByPk(TipoFormaPagoVistaPK tipoFormaPagoVistaPK);
    default LogicBusinessException paymentMethodNotFoundException() {
        return new LogicBusinessException(FORMA_PAGO_NO_EXISTE_14);
    }
}
