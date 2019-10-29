package com.gestorinc.repository;

import com.gestorinc.repository.entity.Cliente;
import com.gestorinc.repository.entity.ClientePK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository extends CrudRepository<Cliente, ClientePK> {

    @Query("select c from Cliente c where c.pk.codigoPersona = ?1 and c.estadoParticipe = 'A' ")
    List<Cliente> findByCodPersona(Long codPersona);

    @Query("select c from Cliente c, Producto p " +
            "where c.pk.numLicencia = p.pk.numLicencia and c.pk.codigoEmpresa = p.pk.codigoEmpresa " +
            " and c.pk.codigoProducto = p.pk.codigoProducto and c.pk.codigoPersona = ?1 and c.estadoParticipe = 'A' " +
            " and p.gLN = ?2 ")
    List<Cliente> findByCodPersonaAndGLNCode(Long codPersona, String gLNCode);
}
