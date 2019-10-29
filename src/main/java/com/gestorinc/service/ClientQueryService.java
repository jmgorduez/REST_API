package com.gestorinc.service;

import com.gestorinc.exception.LogicBusinessException;
import com.gestorinc.repository.IProductRepository;
import com.gestorinc.repository.entity.Cliente;
import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.repository.entity.Persona;
import com.gestorinc.repository.entity.Producto;
import com.gestorinc.service.abstractions.IClientManager;
import com.gestorinc.service.abstractions.IClientQueryService;
import com.gestorinc.service.abstractions.IContributionIntentionManager;
import com.gestorinc.service.dto.ClientQueryClientIdServiceResponseDTO;
import com.gestorinc.service.dto.ClientQueryNPEServiceResponseDTO;
import com.gestorinc.service.dto.SavingFundAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.gestorinc.exception.enums.Error.FONDO_SIN_GLN_CONFIGURADO_18;
import static com.gestorinc.exception.enums.Error.PRODUCTO_CON_GLN_NO_ENCONTRADO;
import static com.gestorinc.utils.Constants.*;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service
public class ClientQueryService implements IClientQueryService {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IContributionIntentionManager contributionIntentionManager;
    @Autowired
    private IClientManager clientManager;


    @Override
    public ClientQueryNPEServiceResponseDTO queryByNPE(String npe) {

        IntencionAporte intencionAporte =
                contributionIntentionManager.getContributionIntention(npe);
        Persona persona = clientManager.getPerson(intencionAporte.getPersonaPK());

        return buildResponse(intencionAporte, persona);
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

    private String getProductInformationToShow(IntencionAporte intencionAporte) {
        return FONDO_.concat(intencionAporte.getPk().getCodigoProducto()).concat(BLANK_SPACE)
                .concat(intencionAporte.getCuentaParticipe());
    }

    @Override
    public ClientQueryClientIdServiceResponseDTO queryByClientId(String clientId, String gLNCode) {

        ofNullable(gLNCode)
                .ifPresent(productRepository::validateIfExistProductWithGNL);

        List<Cliente> clientList = clientManager.getClientList(clientId, gLNCode);
        List<SavingFundAccountDTO> participantAccountToShow = clientList.stream()
                .filter(this::hasAccountNumber)
                .sorted(Cliente::compareTo)
                .map(this::toParticipantAccount)
                .collect(toList());
        String participantAccounts = clientList.stream()
                .map(Cliente::getNumeroCuenta)
                .sorted(String::compareTo).collect(Collectors.joining(BLANK_SPACE));
        String productCodes = clientList.stream()
                .map(this::toProductCode)
                .distinct()
                .sorted(String::compareTo).collect(Collectors.joining(BLANK_SPACE));

        return buildResponse(participantAccountToShow, participantAccounts, productCodes);
    }

    private boolean hasAccountNumber(Cliente cliente){
        return !StringUtils.isEmpty(cliente.getNumeroCuenta());
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

    private SavingFundAccountDTO toParticipantAccount(Cliente cliente) {

        Producto producto = productRepository.findOne(cliente.getPk().getProductoPK());
        return SavingFundAccountDTO.builder()
                .accountDescription(FONDO_.concat(cliente.getPk().getCodigoProducto()).concat(BLANK_SPACE)
                        .concat(maskParticipantAccount(cliente.getNumeroCuenta())))
                .accountNumber(cliente.getNumeroCuenta())
                .gLNCode(ofNullable( producto.getGLN())
                        .filter(Objects::nonNull)
                        .map(integer -> integer.toString())
                        .orElseThrow(this::gLNNotFound))
                .build();
    }

    private LogicBusinessException gLNNotFound(){
        return new LogicBusinessException(FONDO_SIN_GLN_CONFIGURADO_18);
    }

    private String toString(Integer integer){
        return integer.toString();
    }

    private String toProductCode(Cliente cliente) {
        return cliente.getPk().getCodigoProducto();
    }

    private String maskParticipantAccount(String participantAccount) {
        return _____.concat(participantAccount.substring(participantAccount.length() - 4));
    }
}
