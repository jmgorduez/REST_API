package com.gestorinc.repository;

import com.gestorinc.repository.entity.NotificacionAporte;
import com.gestorinc.repository.entity.NotificacionAportePK;
import org.springframework.data.repository.CrudRepository;

public interface IContributionPetitionRepository extends CrudRepository<NotificacionAporte, NotificacionAportePK> {
}
