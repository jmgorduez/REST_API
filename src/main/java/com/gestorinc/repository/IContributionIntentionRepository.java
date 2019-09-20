package com.gestorinc.repository;

import com.gestorinc.repository.entity.ContributionIntention;
import org.springframework.data.repository.CrudRepository;

public interface IIntencionAporteRepository extends CrudRepository<ContributionIntention, String> {
    ContributionIntention findByNPE(String npe);
}
