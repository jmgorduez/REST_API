package com.gestorinc.service.abstractions;

import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.service.dto.ContributionNotificationServiceResponseDTO;

import java.util.Date;

public interface IContributionIntentionManager {
    IntencionAporte getContributionIntention(String npe);
    void save(IntencionAporte intencionAporte);
}
