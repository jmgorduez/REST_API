package com.gestorinc.service.abstractions;

import com.gestorinc.controller.model.ConsultaClienteResponse;

public interface IConsultaClienteBuilder {

    ConsultaClienteResponse construirPorNPE(String npe);
    ConsultaClienteResponse construirPorIdCliente(String idCliente);
}
