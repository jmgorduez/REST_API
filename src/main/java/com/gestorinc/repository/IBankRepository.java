package com.gestorinc.repository;

import com.gestorinc.repository.entity.CredencialesBancarias;
import com.gestorinc.repository.entity.CredencialesBancariasPK;
import com.gestorinc.repository.entity.PersonaIdentificacion;
import com.gestorinc.repository.entity.PersonaIdentificacionPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBankRepository extends CrudRepository<CredencialesBancarias, CredencialesBancariasPK> {

    Optional<CredencialesBancarias> findByCodigoAcceso(String codigo);
}
