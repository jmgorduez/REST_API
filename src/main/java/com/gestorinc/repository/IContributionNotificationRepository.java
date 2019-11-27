package com.gestorinc.repository;

import com.gestorinc.exception.LogicBusinessException;
import com.gestorinc.repository.entity.NotificacionAporte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

import static com.gestorinc.exception.enums.Error.FECHA_DIA_FERIADO_24;

@Repository
public interface IContributionNotificationRepository extends CrudRepository<NotificacionAporte, Long> {

    Optional<NotificacionAporte> findBySecNotificacion(Long id);

    @Query(" select max(notif.secNotificacion) + 1 from NotificacionAporte notif ")
    Optional<Long> nextSequence();

    @Query(" select nA from NotificacionAporte nA where nA.nPE = REPLACE( TRIM(?1), ' ', '') ")
    Optional<NotificacionAporte> findByNPE(String npe);

    @Query(nativeQuery = true,
            value = "select be_k_general.isHoliday(?1) from dual")
    boolean isHoliday(Date date);

    default void validateHoliday(Date date) {
        if (isHoliday(date)){
            throw new LogicBusinessException(FECHA_DIA_FERIADO_24);
        }
    }
}
