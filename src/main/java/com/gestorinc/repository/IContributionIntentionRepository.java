package com.gestorinc.repository;

import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.repository.entity.IntencionAportePK;
import org.springframework.data.repository.CrudRepository;

public interface IContributionIntentionRepository extends CrudRepository<IntencionAporte, IntencionAportePK> {
     IntencionAporte findByNPE(String npe);
}
