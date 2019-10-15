package com.gestorinc.service;

import com.gestorinc.exception.LogicBusinessException;
import com.gestorinc.repository.IClientRepository;
import com.gestorinc.repository.IPersonIdentificationRepository;
import com.gestorinc.repository.IPersonRepository;
import com.gestorinc.repository.entity.Cliente;
import com.gestorinc.repository.entity.Persona;
import com.gestorinc.repository.entity.PersonaPK;
import com.gestorinc.service.abstractions.IClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static com.gestorinc.exception.enums.Error.*;
import static com.gestorinc.utils.Constants.*;

@Component
public class ClientManager implements IClientManager {

    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private IPersonIdentificationRepository personIdentificationRepository;
    @Autowired
    private IClientRepository clientRepository;

    @Override
    public Persona getPerson(PersonaPK personaPK) {

        Persona persona = personRepository.findOne(personaPK);
        validateLocalAdultClientHasDUIIdentificationType(persona);
        return persona;
    }

    @Override
    public void validateLocalAdultClientHasDUIIdentificationType(Persona persona) {
        if (isLocal(persona) && isAdult(persona)) {
            personIdentificationRepository.findPersonIdentification(persona.getPk().getNumLicencia(),
                    persona.getPk().getCodigoPersona(), DUI_CODE)
                    .orElseThrow(this::localAdultClientWithoutDUIIdentificationException);
        }
    }

    @Override
    public List<Cliente> getClientList(String clientId) {

        Persona persona = personRepository.findByIdentification(clientId)
                .orElseThrow(this::clientNotFoundException);

        validateLocalAdultClientHasDUIIdentificationType(persona);

        List<Cliente> clientList = clientRepository.findByCodPersona(persona.getPk().getCodigoPersona());

        if (clientList.isEmpty()) {
            throw this.clientDoesNotHaveAActiveAccountException();
        }

        return clientList;
    }

    @Override
    public Cliente getClient(String clientId, String participantAccount) {
        return getClientList(clientId).stream()
                .filter(client -> client.getNumeroCuenta().equals(participantAccount))
                .findFirst()
                .orElseThrow(this::participantAccountNotFoundException);
    }

    private LogicBusinessException participantAccountNotFoundException() {
        return new LogicBusinessException(CLIENTE_NO_TIENE_CUENTA_REGISTRADA);
    }

    private boolean isLocal(Persona persona) {
        return persona.getLocal().getVerdadero();
    }

    private boolean isAdult(Persona persona) {

        LocalDate birthLocalDate = LocalDate.parse(new SimpleDateFormat(YYYY_MM_DD)
                .format(persona.getFechaNacimiento()));
        return !(birthLocalDate.plusYears(18).isAfter(LocalDate.now()));
    }

    private LogicBusinessException clientNotFoundException() {
        return new LogicBusinessException(CLIENTE_NO_ENCONTRADO_COD_4);
    }

    private LogicBusinessException localAdultClientWithoutDUIIdentificationException() {
        return new LogicBusinessException(CLIENTE_DEBE_ACTUALIZAR_SU_INFORMACIÓN_EN_CONFIA_PARA_PODER_REALIZAR_TRANSACCIONES_COD_9);
    }

    private LogicBusinessException clientDoesNotHaveAActiveAccountException() {
        return new LogicBusinessException(CLIENTE_NO_POSEE_CUENTA_ACTIVA_COD_5);
    }
}
