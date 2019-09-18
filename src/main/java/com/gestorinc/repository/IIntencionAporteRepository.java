package com.gestorinc.repository;

import com.gestorinc.repository.entity.IntencionAporte;
import org.springframework.data.repository.CrudRepository;

public interface IIntencionAporteRepository extends CrudRepository<IntencionAporte, String> {
    IntencionAporte findByNPE(String npe);
}
