package com.gestorinc.service;

import com.gestorinc.exception.LogicBusinessException;
import com.gestorinc.exception.enums.Error;
import com.gestorinc.repository.IContributionNotificationRepository;
import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.repository.entity.NotificacionAporte;
import com.gestorinc.service.abstractions.IContributionConfirmationService;
import com.gestorinc.service.abstractions.IContributionIntentionManager;
import com.gestorinc.service.dto.ContributionConfirmationServiceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.gestorinc.exception.enums.Error.*;
import static com.gestorinc.repository.entity.enums.EnumEstadoNotificacionAporte.CNF;
import static java.util.Optional.ofNullable;

@Service
public class ContributionConfirmationService implements IContributionConfirmationService {

    @Autowired
    private IContributionNotificationRepository contributionNotificationRepository;
    @Autowired
    private IContributionIntentionManager contributionIntentionManager;

    @Override
    public ContributionConfirmationServiceResponseDTO confirmContribution(Long correlative,
                                                                          String reference) {
        NotificacionAporte notificacionAporte =
                contributionNotificationRepository.findBySecNotificacion(correlative)
                        .orElseThrow(this::contributionNotificationNotFound);
        this.validateNotificationStatus(notificacionAporte);

        notificacionAporte.setEstado(CNF);
        notificacionAporte.setReferencia(reference);
        contributionNotificationRepository.save(notificacionAporte);

        ofNullable(notificacionAporte.getNPE())
                .ifPresent(contributionIntentionManager::markContributionIntentionAsRES);

        return ContributionConfirmationServiceResponseDTO.builder()
                .productCodeAud(notificacionAporte.getCodigoProducto())
                .participantAccountAud(notificacionAporte.getNumeroCuenta())
                .build();
    }

    private void validateNotificationStatus(NotificacionAporte notificacionAporte) {

        switch (notificacionAporte.getEstado()) {
            case CNF:
                throw this.confirmedNotificationException();
            case PRO:
                throw this.processedNotificationException();
        }
    }

    private LogicBusinessException confirmedNotificationException() {
        return new LogicBusinessException(NOTIFICACION_CONFIRMADA_21);
    }

    private LogicBusinessException processedNotificationException() {
        return new LogicBusinessException(NOTIFICACION_PROCESADA_22);
    }


    private LogicBusinessException contributionNotificationNotFound() {
        return new LogicBusinessException(NO_EXISTE_UNA_NOTIFICACION_DE_APORTE_EN_ESTADO_INGRESADA_CON_ESTE_CORRELATIVO_COD_13);
    }
}
