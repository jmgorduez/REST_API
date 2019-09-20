package com.gestorinc.repository;

import com.gestorinc.repository.entity.ContributionIntention;
import org.springframework.data.repository.CrudRepository;

public interface IClientRepository extends CrudRepository<ContributionIntention, Long> {
    ContributionIntention findByNPE(String npe);
}
