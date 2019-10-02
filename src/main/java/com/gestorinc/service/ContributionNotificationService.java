package com.gestorinc.service;

import com.gestorinc.exception.LogicBusinessException;
import com.gestorinc.repository.*;
import com.gestorinc.repository.entity.*;
import com.gestorinc.repository.entity.enums.EnumEstadoNotificacionAporte;
import com.gestorinc.service.abstractions.IContributionNotificationService;
import com.gestorinc.service.dto.ContributionNotificationServiceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

import static com.gestorinc.exception.enums.Error.*;

@Service
public class ContributionNotificationService implements IContributionNotificationService {

    @Autowired
    private IContributionIntentionRepository contributionIntentionRepository;
    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private IPersonIdentificationRepository personIdentificationRepository;
    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;
    @Autowired
    private IContributionNotificationRepository contributionNotificationRepository;


    @Override
    public ContributionNotificationServiceResponseDTO contributionNotificationByNPE(String npe,
                                                                                    Date contributionDate,
                                                                                    String paymentMethodCode) {

        IntencionAporte intencionAporte = contributionIntentionRepository.findByNPE(npe)
                .orElseThrow(this::nPENotFoundException);

        Long secNotification =
                saveContributionNotification(contributionDate, paymentMethodCode, intencionAporte);

        return ContributionNotificationServiceResponseDTO.builder()
                .correlative(secNotification)
                .participantAccountAud(intencionAporte.getCuentaParticipe())
                .productCodeAud(intencionAporte.getPk().getCodigoProducto())
                .build();
    }

    private synchronized Long saveContributionNotification(Date contributionDate, String paymentMethodCode, IntencionAporte intencionAporte) {

        paymentMethodRepository.findByPk(new TipoFormaPagoVistaPK(intencionAporte.getPk().getNumLicencia(),
                paymentMethodCode)).orElseThrow(this::paymentMethodNotFoundException);

        Long secNotification = getNextSequence(intencionAporte);

        NotificacionAporte notificacionAporte =
                buildContributionNotification(contributionDate, paymentMethodCode, intencionAporte, secNotification);

        contributionNotificationRepository.save(notificacionAporte);

        return secNotification;
    }

    private NotificacionAporte buildContributionNotification(Date contributionDate, String paymentMethodCode, IntencionAporte intencionAporte, Long secNotificacion) {
        return NotificacionAporte.builder()
                .pk(intencionAporte.getNotificacionAportePK(secNotificacion))
                .nPE(intencionAporte.getNPE())
                .codigoPersona(intencionAporte.getCodigoPersona())
                .numeroCuenta(intencionAporte.getCuentaParticipe())
                .monto(intencionAporte.getMonto())
                .codigoMoneda(intencionAporte.getCodigoMoneda())
                .estado(EnumEstadoNotificacionAporte.ING)
                .fechaHoraAporte(contributionDate)
                .fechaHoraRegistro(Calendar.getInstance().getTime())
                .codigoFormaPago(paymentMethodCode)
                .build();
    }

    private Long getNextSequence(IntencionAporte intencionAporte) {
        return contributionNotificationRepository.nextSequence(
                intencionAporte.getPk().getNumLicencia(),
                intencionAporte.getPk().getCodigoEmpresa(),
                intencionAporte.getPk().getCodigoProducto()).orElse(1l);
    }

    private LogicBusinessException nPENotFoundException() {
        return new LogicBusinessException(NPE_NO_ENCONTRADO_COD_3);
    }

    private LogicBusinessException paymentMethodNotFoundException() {
        return new LogicBusinessException(FORMA_PAGO_NO_EXISTE_14);
    }

    @Override
    public ContributionNotificationServiceResponseDTO contributionNotificationByClientId(String clientId) {
        return null;
    }
}
