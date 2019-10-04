package com.gestorinc.service.abstractions;

import com.gestorinc.repository.entity.IntencionAporte;

public interface IContributionIntentionManager {
    IntencionAporte getContributionIntention(String npe);
    void markContributionIntentionAsRES(String npe);
    void markContributionIntentionAsNTF(String npe);
}
