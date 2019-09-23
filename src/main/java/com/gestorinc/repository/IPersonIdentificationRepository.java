package com.gestorinc.repository;

import com.gestorinc.repository.entity.Persona;
import com.gestorinc.repository.entity.PersonaIdentificacion;
import com.gestorinc.repository.entity.PersonaIdentificacionPK;
import com.gestorinc.repository.entity.PersonaPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonIdentificationRepository extends CrudRepository<PersonaIdentificacion, PersonaIdentificacionPK> {

    @Query(value = "select pId from PersonaIdentificacion pId " +
            " where pId.pk.numLicencia = ?1 " +
            "and pId.pk.codigoPersona = ?2 " +
            "and pId.pk.codigoTipoIdentificacion = ?3 ")
    Optional<PersonaIdentificacion> findPersonIdentification(Integer licenceNum, Long personCode, String idTypeCode);
}
