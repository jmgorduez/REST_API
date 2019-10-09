package com.gestorinc.service;

import com.gestorinc.exception.LogicBusinessException;
import com.gestorinc.repository.IContributionNotificationRepository;
import com.gestorinc.repository.IPaymentMethodRepository;
import com.gestorinc.repository.IProductRepository;
import com.gestorinc.repository.entity.*;
import com.gestorinc.repository.entity.enums.EnumEstadoNotificacionAporte;
import com.gestorinc.service.abstractions.IClientManager;
import com.gestorinc.service.abstractions.IContributionIntentionManager;
import com.gestorinc.service.abstractions.IContributionNotificationService;
import com.gestorinc.service.dto.ContributionNotificationServiceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static com.gestorinc.exception.enums.Error.FORMA_PAGO_NO_EXISTE_14;
import static com.gestorinc.exception.enums.Error.PRODUCTO_CON_GLN_NO_ENCONTRADO;

@Service
public class ContributionNotificationService implements IContributionNotificationService {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;
    @Autowired
    private IContributionNotificationRepository contributionNotificationRepository;
    @Autowired
    private IContributionIntentionManager contributionIntentionManager;
    @Autowired
    private IClientManager clientManager;


    @Override
    public ContributionNotificationServiceResponseDTO contributionNotificationByNPE(String npe,
                                                                                    Date contributionDate,
                                                                                    String paymentMethodCode) {

        IntencionAporte intencionAporte = contributionIntentionManager.getContributionIntention(npe);

        clientManager.validateLocalAdultClientHasDUIIdentificationType(
                clientManager.getPerson(intencionAporte.getPersonaPK()));

        Long secNotification =
                saveContributionNotificationByClientId(contributionDate, paymentMethodCode, intencionAporte);

        return ContributionNotificationServiceResponseDTO.builder()
                .correlative(secNotification)
                .participantAccountAud(intencionAporte.getCuentaParticipe())
                .productCodeAud(intencionAporte.getPk().getCodigoProducto())
                .build();
    }

    @Transactional
    private synchronized Long saveContributionNotificationByClientId(Date contributionDate,
                                                                     String paymentMethodCode,
                                                                     IntencionAporte intencionAporte) {

        validateExistPaymentMethod(intencionAporte.getPk().getNumLicencia(), paymentMethodCode);

        Long secNotification = contributionNotificationRepository.nextSequence()
                .orElse(1l);

        contributionNotificationRepository.findAll().forEach(notificacionAporte -> {
            System.out.println("SEC: " + notificacionAporte.getSecNotificacion().toString()
                    .concat(notificacionAporte.getNumeroCuenta())
                    .concat(notificacionAporte.getEstado().name()).concat(notificacionAporte.getCodigoPersona().toString()));
        });

        NotificacionAporte notificacionAporte =
                buildContributionNotification(contributionDate, paymentMethodCode, intencionAporte, secNotification);

        contributionNotificationRepository.save(notificacionAporte);

        contributionIntentionManager.markContributionIntentionAsNTF(intencionAporte.getNPE());

        return secNotification;
    }

    private void validateExistPaymentMethod(Integer licenceNumber, String paymentMethodCode) {
        paymentMethodRepository.findByPk(new TipoFormaPagoVistaPK(licenceNumber, paymentMethodCode))
                .orElseThrow(this::paymentMethodNotFoundException);
    }

    private NotificacionAporte buildContributionNotification(Date contributionDate, String paymentMethodCode,
                                                             IntencionAporte intencionAporte, Long secNotificacion) {
        return NotificacionAporte.builder()
                .secNotificacion(secNotificacion)
                .numLicencia(intencionAporte.getPk().getNumLicencia())
                .codigoEmpresa(intencionAporte.getPk().getCodigoEmpresa())
                .codigoProducto(intencionAporte.getPk().getCodigoProducto())
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

    private NotificacionAporte buildContributionNotification(Producto producto, Long secNotificacion,
                                                             Long personCode, String participantAccount,
                                                             Date contributionDate, BigDecimal amount,
                                                             String paymentMethodCode) {
        return NotificacionAporte.builder()
                .numLicencia(producto.getPk().getNumLicencia())
                .codigoEmpresa(producto.getPk().getCodigoEmpresa())
                .codigoProducto(producto.getPk().getCodigoProducto())
                .secNotificacion(secNotificacion)
                .nPE(null)
                .codigoPersona(personCode)
                .numeroCuenta(participantAccount)
                .monto(amount)
                .codigoMoneda(producto.getCodigoMoneda())
                .estado(EnumEstadoNotificacionAporte.ING)
                .fechaHoraAporte(contributionDate)
                .fechaHoraRegistro(Calendar.getInstance().getTime())
                .codigoFormaPago(paymentMethodCode)
                .build();
    }

    private LogicBusinessException paymentMethodNotFoundException() {
        return new LogicBusinessException(FORMA_PAGO_NO_EXISTE_14);
    }

    @Override
    public ContributionNotificationServiceResponseDTO contributionNotificationByClientId(String clientId,
                                                                                         Date contributionDate,
                                                                                         String paymentMethodCode,
                                                                                         String participantAccount,
                                                                                         BigDecimal amount,
                                                                                         Integer gNLCode) {
        Producto producto = productRepository.findByGLN(gNLCode)
                .orElseThrow(this::productWithGLNNotFoundException);

        Long secNotification = saveContributionNotificationByClientId(clientId, contributionDate, paymentMethodCode,
                participantAccount,amount, producto);

        return ContributionNotificationServiceResponseDTO.builder()
                .correlative(secNotification)
                .participantAccountAud(participantAccount)
                .productCodeAud(producto.getPk().getCodigoProducto())
                .build();
    }

    @Transactional
    private synchronized Long saveContributionNotificationByClientId(String clientId, Date contributionDate,
                                                                     String paymentMethodCode, String participantAccount,
                                                                     BigDecimal amount, Producto producto) {
        Cliente cliente = clientManager.getClient(clientId, participantAccount);

        validateExistPaymentMethod(producto.getPk().getNumLicencia(), paymentMethodCode);

        Long secNotification = contributionNotificationRepository.nextSequence()
                .orElse(1l);

        NotificacionAporte notificacionAporte =
                buildContributionNotification(producto, secNotification, cliente.getPk().getCodigoPersona(),
                        participantAccount, contributionDate, amount, paymentMethodCode);

        contributionNotificationRepository.save(notificacionAporte);

        return secNotification;
    }

    private LogicBusinessException productWithGLNNotFoundException() {
        return new LogicBusinessException(PRODUCTO_CON_GLN_NO_ENCONTRADO);
    }
}
