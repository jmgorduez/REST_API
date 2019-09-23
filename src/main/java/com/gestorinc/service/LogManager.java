package com.gestorinc.service;

import com.gestorinc.repository.IInterfaceLogRepository;
import com.gestorinc.repository.entity.LogInterfaz;
import com.gestorinc.service.abstractions.ILogManager;
import com.gestorinc.service.dto.AuditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogManager implements ILogManager {

    @Autowired
    private IInterfaceLogRepository interfaceLogRepository;


    @Override
    public synchronized void saveLog(AuditDTO auditDTO) {

        Long autoIncrementedId = interfaceLogRepository.nextSequence() != null ?
                interfaceLogRepository.nextSequence() :
                1L;

        LogInterfaz logInterfaz =
                new LogInterfaz(autoIncrementedId,
                        auditDTO.getUser(), auditDTO.getOperation(), auditDTO.getProduct(),
                        auditDTO.getParticipant(), auditDTO.getJsonRequestFrame(), auditDTO.getJsonResponseFrame(),
                        LogInterfaz.EstadoLog.valueOf(auditDTO.getStatus().name()), auditDTO.getMessage());
        interfaceLogRepository.save(logInterfaz);
    }
}
