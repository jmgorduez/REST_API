package com.gestorinc.service.abstractions;

import com.gestorinc.service.dto.ContributionNotificationServiceResponseDTO;

import java.math.BigDecimal;
import java.util.Date;

public interface IContributionNotificationService {
    ContributionNotificationServiceResponseDTO contributionNotificationByNPE(String npe,
                                                                             Date contributionDate,
                                                                             String paymentMethodCode);
    ContributionNotificationServiceResponseDTO contributionNotificationByClientId(String clientId,
                                                                                  Date contributionDate,
                                                                                  String paymentMethodCode,
                                                                                  String participantAccount,
                                                                                  BigDecimal amount,
                                                                                  Integer gNLCode);
}
