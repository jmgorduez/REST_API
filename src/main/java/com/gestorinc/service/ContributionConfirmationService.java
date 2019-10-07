package com.gestorinc.service;

import com.gestorinc.exception.LogicBusinessException;
import com.gestorinc.repository.IContributionNotificationRepository;
import com.gestorinc.repository.entity.NotificacionAporte;
import com.gestorinc.service.abstractions.IContributionConfirmationService;
import com.gestorinc.service.abstractions.IContributionIntentionManager;
import com.gestorinc.service.dto.ContributionConfirmationServiceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.gestorinc.exception.enums.Error.NOTIFICACION_APORTE_NO_EXISTE_17;
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

    private LogicBusinessException contributionNotificationNotFound() {
        return new LogicBusinessException(NOTIFICACION_APORTE_NO_EXISTE_17);
    }
}
