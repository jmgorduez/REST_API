package com.gestorinc.repository;

import com.gestorinc.repository.entity.NotificacionAporte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IContributionNotificationRepository extends CrudRepository<NotificacionAporte, Long> {

    Optional<NotificacionAporte> findBySecNotificacion(Long id);

    @Query(" select max(notif.secNotificacion) + 1 from NotificacionAporte notif ")
    Optional<Long> nextSequence();

    Optional<NotificacionAporte> findByNPE(String npe);
}
