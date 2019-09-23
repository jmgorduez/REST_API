package com.gestorinc.repository;

import com.gestorinc.repository.entity.Persona;
import com.gestorinc.repository.entity.PersonaPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonRepository extends CrudRepository<Persona, PersonaPK> {

    @Query(value = "select distinct(p) from PersonaIdentificacion pId, " +
            "Persona p where pId.pk.numLicencia = p.pk.numLicencia " +
            "and pId.pk.codigoPersona = p.pk.codigoPersona " +
            "and pId.identificacion = ?1")
    Optional<Persona> findByIdentification(String id);

    @Query(value = "select distinct(p) from PersonaIdentificacion pId, " +
            "Persona p where pId.pk.numLicencia = p.pk.numLicencia " +
            "and pId.pk.codigoPersona = p.pk.codigoPersona " +
            "and pId.pk.codigoTipoIdentificacion = ?2 " +
            "and p.pk.codigoPersona = ?1")
    Boolean personHasThisIdentificationType(Long personCode, String typeId);
}
