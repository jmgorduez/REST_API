package com.gestorinc.service;

import com.gestorinc.repository.IContributionIntentionRepository;
import com.gestorinc.repository.IPersonRepository;
import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.repository.entity.Persona;
import com.gestorinc.service.abstractions.IClientQueryService;
import com.gestorinc.service.dto.ClientQueryClientIdResponseDTO;
import com.gestorinc.service.dto.ClientQueryNPEResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.gestorinc.utils.Constants.BLANK_SPACE;
import static com.gestorinc.utils.Constants.FONDO_;

@Service
public class ClientQueryService implements IClientQueryService {

    private final IContributionIntentionRepository contributionIntentionRepository;
    private final IPersonRepository personRepository;

    @Autowired
    public ClientQueryService(IContributionIntentionRepository contributionIntentionRepository,
                              IPersonRepository personRepository){
        this.contributionIntentionRepository = contributionIntentionRepository;
        this.personRepository = personRepository;
    }

    @Override
    public ClientQueryNPEResponseDTO queryByNPE(String npe) {
        IntencionAporte intencionAporte = contributionIntentionRepository.findByNPE(npe);
        Persona participe = personRepository.findOne(intencionAporte.getPersonaPK());
        return ClientQueryNPEResponseDTO.builder()
                .name(participe.getNombres())
                .product(getProductInformationToShow(intencionAporte))
                .amount(intencionAporte.getMonto())
                .build();
    }

    private String getProductInformationToShow(IntencionAporte intencionAporte) {
        return FONDO_.concat(intencionAporte.getCodigoProducto()).concat(BLANK_SPACE).concat(intencionAporte.getCuentaParticipe());
    }

    @Override
    public ClientQueryClientIdResponseDTO QueryByClientId(String clientId) {
        return null;
    }
}
