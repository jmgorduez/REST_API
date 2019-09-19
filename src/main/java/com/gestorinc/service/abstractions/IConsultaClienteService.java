package com.gestorinc.service.abstractions;

import com.gestorinc.service.dto.ConsultaClienteIdClienteRespuestaDTO;
import com.gestorinc.service.dto.ConsultaClienteNPERespuestaDTO;

public interface IConsultaClienteService {
    ConsultaClienteNPERespuestaDTO consultarPorNPE(String npe);
    ConsultaClienteIdClienteRespuestaDTO consultarPorIdCliente(String idCliente);
}
