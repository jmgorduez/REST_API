package com.gestorinc.repository;

import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.repository.entity.IntencionAportePK;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IContributionIntentionRepository extends CrudRepository<IntencionAporte, IntencionAportePK> {
     Optional<IntencionAporte> findByNPE(String npe);
}
