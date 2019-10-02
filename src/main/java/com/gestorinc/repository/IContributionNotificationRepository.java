package com.gestorinc.repository;

import com.gestorinc.repository.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IContributionNotificationRepository extends CrudRepository<NotificacionAporte, NotificacionAportePK> {

    Optional<NotificacionAporte> findByPk(NotificacionAportePK tipoFormaPagoVistaPK);

    @Query(" select max(notif.pk.secNotificacion) + 1 from NotificacionAporte notif " +
            " where notif.pk.numLicencia = ?1 and notif.pk.codigoEmpresa = ?2 " +
            " and notif.pk.codigoProducto = ?3 ")
    Optional<Long> nextSequence(Integer licenceNumber, String enterpriseCode, String productCode);

    Optional<NotificacionAporte> findByNPE(String npe);
}
