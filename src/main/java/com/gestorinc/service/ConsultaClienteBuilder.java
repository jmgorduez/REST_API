package com.gestorinc.service;

import com.gestorinc.controller.model.ConsultaClienteResponse;
import com.gestorinc.repository.IIntencionAporteRepository;
import com.gestorinc.service.abstractions.IConsultaClienteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaClienteBuilder implements IConsultaClienteBuilder {

    private final IIntencionAporteRepository intencionAporteRepository;

    @Autowired
    public ConsultaClienteBuilder(IIntencionAporteRepository intencionAporteRepository) {
        this.intencionAporteRepository = intencionAporteRepository;
    }

    @Override
    public ConsultaClienteResponse construirPorNPE(String npe) {
        return null;
    }

    @Override
    public ConsultaClienteResponse construirPorIdCliente(String idCliente) {
        return null;
    }
}
