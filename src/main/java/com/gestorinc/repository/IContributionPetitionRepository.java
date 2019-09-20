package com.gestorinc.repository;

import com.gestorinc.repository.entity.SolicitudAporte;
import org.springframework.data.repository.CrudRepository;

public interface IContributionPetitionRepository extends CrudRepository<SolicitudAporte, Integer> {
}
