package com.gestorinc.repository;

import com.gestorinc.repository.entity.Persona;
import com.gestorinc.repository.entity.PersonaPK;
import org.springframework.data.repository.CrudRepository;

public interface IPersonRepository extends CrudRepository<Persona, PersonaPK> {
}
