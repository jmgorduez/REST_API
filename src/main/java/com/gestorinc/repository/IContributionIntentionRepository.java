package com.gestorinc.repository;

import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.repository.entity.IntencionAportePK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IContributionIntentionRepository extends CrudRepository<IntencionAporte, IntencionAportePK> {

     @Query(" select iA from IntencionAporte iA where iA.nPE = REPLACE( TRIM(?1), ' ', '') ")
     Optional<IntencionAporte> findByNPE(String npe);
}
