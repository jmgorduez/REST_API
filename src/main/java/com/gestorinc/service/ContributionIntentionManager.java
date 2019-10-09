package com.gestorinc.service;

import com.gestorinc.exception.LogicBusinessException;
import com.gestorinc.repository.IContributionIntentionRepository;
import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.service.abstractions.IContributionIntentionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

import static com.gestorinc.exception.enums.Error.*;
import static com.gestorinc.repository.entity.enums.EnumEstadoIntencionAporte.NTF;
import static com.gestorinc.repository.entity.enums.EnumEstadoIntencionAporte.RES;

@Component
public class ContributionIntentionManager implements IContributionIntentionManager {

    @Autowired
    private IContributionIntentionRepository contributionIntentionRepository;

    @Override
    public IntencionAporte getContributionIntention(String npe) {
        IntencionAporte intencionAporte = contributionIntentionRepository.findByNPE(npe)
                .orElseThrow(this::NPENotFoundException);
        validateNPEStatus(intencionAporte);
        return intencionAporte;
    }

    @Override
    public void markContributionIntentionAsRES(String npe) {
        IntencionAporte intencionAporte =
                contributionIntentionRepository.findByNPE(npe)
                        .orElseThrow(EntityNotFoundException::new);
        intencionAporte.setEstado(RES);
        contributionIntentionRepository.save(intencionAporte);
    }

    @Override
    public void markContributionIntentionAsNTF(String npe) {
        IntencionAporte intencionAporte =
                contributionIntentionRepository.findByNPE(npe)
                        .orElseThrow(EntityNotFoundException::new);
        intencionAporte.setEstado(NTF);
        contributionIntentionRepository.save(intencionAporte);
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
}
