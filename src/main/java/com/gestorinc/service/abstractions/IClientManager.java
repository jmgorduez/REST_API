package com.gestorinc.service.abstractions;

import com.gestorinc.repository.entity.Cliente;
import com.gestorinc.repository.entity.Persona;
import com.gestorinc.repository.entity.PersonaPK;

import java.util.List;

public interface IClientManager {
    Persona getPerson(PersonaPK personaPK);
    List<Cliente> getClientList(String clientId, String... gLNCode);
    Cliente getClient(String clientId, String participantAccount);
    void validateLocalAdultClientHasDUIIdentificationType(Persona persona);
}
