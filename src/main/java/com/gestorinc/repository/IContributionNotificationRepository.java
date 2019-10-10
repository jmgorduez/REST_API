package com.gestorinc.repository;

import com.gestorinc.repository.entity.NotificacionAporte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IContributionNotificationRepository extends CrudRepository<NotificacionAporte, Long> {

    Optional<NotificacionAporte> findBySecNotificacion(Long id);

    @Query(" select max(notif.secNotificacion) + 1 from NotificacionAporte notif ")
    Optional<Long> nextSequence();

    @Query(" select nA from NotificacionAporte nA where nA.nPE = REPLACE( TRIM(?1), ' ', '') ")
    Optional<NotificacionAporte> findByNPE(String npe);
}
