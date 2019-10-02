package com.gestorinc.service;

import com.gestorinc.exception.LogicBusinessException;
import com.gestorinc.repository.*;
import com.gestorinc.repository.entity.Cliente;
import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.repository.entity.Persona;
import com.gestorinc.repository.entity.Producto;
import com.gestorinc.service.abstractions.IClientQueryService;
import com.gestorinc.service.dto.ClientQueryClientIdServiceResponseDTO;
import com.gestorinc.service.dto.ClientQueryNPEServiceResponseDTO;
import com.gestorinc.service.dto.SavingFundAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.gestorinc.exception.enums.Error.*;
import static com.gestorinc.utils.Constants.*;
import static java.util.stream.Collectors.toList;

@Service
public class ClientQueryService implements IClientQueryService {

    @Autowired
    private IContributionIntentionRepository contributionIntentionRepository;
    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private IPersonIdentificationRepository personIdentificationRepository;
    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private IProductRepository productRepository;


    @Override
    public ClientQueryNPEServiceResponseDTO queryByNPE(String npe) {

        IntencionAporte intencionAporte = contributionIntentionRepository.findByNPE(npe)
                .orElseThrow(this::NPENotFoundException);
        validateNPEStatus(intencionAporte);
        Persona persona = personRepository.findOne(intencionAporte.getPersonaPK());
        validateLocalAdultClientHasDUIIdentificationType(persona);

        return buildResponse(intencionAporte, personRepository.findOne(intencionAporte.getPersonaPK()));
    }

    private ClientQueryNPEServiceResponseDTO buildResponse(IntencionAporte intencionAporte, Persona participe) {
        return ClientQueryNPEServiceResponseDTO.builder()
                .participantName(participe.getNombres())
                .productNameToShow(getProductInformationToShow(intencionAporte))
                .amount(intencionAporte.getMonto())
                .productCodeAud(intencionAporte.getPk().getCodigoProducto())
                .participantAccountAud(intencionAporte.getCuentaParticipe())
                .build();
    }

    private void validateNPEStatus(IntencionAporte intencionAporte) {

        switch (intencionAporte.getEstado()) {
            case PAG:
                throw this.paidNPEException();
            case VEN:
                throw this.defeatedNPEException();
            case NTF:
                throw this.notifiedNPEException();
        }
    }

    private LogicBusinessException NPENotFoundException() {
        return new LogicBusinessException(NPE_NO_ENCONTRADO_COD_3);
    }

    private LogicBusinessException paidNPEException() {
        return new LogicBusinessException(NPE_PAGADO_COD_1);
    }

    private LogicBusinessException defeatedNPEException() {
        return new LogicBusinessException(NPE_VENCIDO_COD_2);
    }

    private LogicBusinessException notifiedNPEException() {
        return new LogicBusinessException(NPE_NOTIFICADO_COD_11);
    }

    private String getProductInformationToShow(IntencionAporte intencionAporte) {
        return FONDO_.concat(intencionAporte.getPk().getCodigoProducto()).concat(BLANK_SPACE)
                .concat(intencionAporte.getCuentaParticipe());
    }

    @Override
    public ClientQueryClientIdServiceResponseDTO queryByClientId(String clientId) {

        List<Cliente> clienteList = getClientList(clientId);
        List<SavingFundAccountDTO> participantAccountToShow = clienteList.stream()
                .sorted(Cliente::compareTo)
                .map(this::toParticipantAccount)
                .collect(toList());
        String participantAccounts = clienteList.stream()
                .map(Cliente::getNumeroCuenta)
                .sorted(String::compareTo).collect(Collectors.joining(BLANK_SPACE));
        String productCodes = clienteList.stream()
                .map(this::toProductCode)
                .distinct()
                .sorted(String::compareTo).collect(Collectors.joining(BLANK_SPACE));

        return buildResponse(participantAccountToShow, participantAccounts, productCodes);
    }

    private ClientQueryClientIdServiceResponseDTO buildResponse(List<SavingFundAccountDTO> participantAccountToShow,
                                                                String participantAccounts,
                                                                String productCodes) {
        return ClientQueryClientIdServiceResponseDTO.builder()
                .savingsFundAccount(participantAccountToShow)
                .productCodeAud(productCodes)
                .participantAccountAud(participantAccounts)
                .build();
    }

    private List<Cliente> getClientList(String clientId) {

        Persona persona = personRepository.findByIdentification(clientId)
                .orElseThrow(this::clientNotFoundException);

        validateLocalAdultClientHasDUIIdentificationType(persona);

        List<Cliente> clientList = clientRepository.findByCodPersona(persona.getPk().getCodigoPersona());

        if (clientList.isEmpty()) {
            throw this.clientDoesNotHaveAActiveAccountException();
        }

        return clientList;
    }

    private void validateLocalAdultClientHasDUIIdentificationType(Persona persona) {
        if (isLocal(persona) && isAdult(persona)) {
            personIdentificationRepository.findPersonIdentification(persona.getPk().getNumLicencia(),
                    persona.getPk().getCodigoPersona(), DUI_CODE)
                    .orElseThrow(this::localAdultClientWithoutDUIIdentificationException);
        }
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


    private SavingFundAccountDTO toParticipantAccount(Cliente cliente) {
        Producto producto = productRepository.findOne(cliente.getPk().getProductoPK());
        return SavingFundAccountDTO.builder()
                .accountDescription(FONDO_.concat(cliente.getPk().getCodigoProducto()).concat(BLANK_SPACE)
                        .concat(maskParticipantAccount(cliente.getNumeroCuenta())))
                .accountNumber(cliente.getNumeroCuenta())
                .gLNCode(producto.getGNL().toString())
                .build();
    }

    private String toProductCode(Cliente cliente) {
        return cliente.getPk().getCodigoProducto();
    }

    private String maskParticipantAccount(String participantAccount) {
        return _____.concat(participantAccount.substring(participantAccount.length() - 4));
    }
}
