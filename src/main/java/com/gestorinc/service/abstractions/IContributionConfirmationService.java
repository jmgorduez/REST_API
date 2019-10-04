package com.gestorinc.service.abstractions;

import com.gestorinc.service.dto.ContributionConfirmationServiceResponseDTO;

public interface IContributionConfirmationService {

    ContributionConfirmationServiceResponseDTO confirmContribution(Long correlative, String reference);
}
