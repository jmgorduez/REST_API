package com.gestorinc.controller;

import com.gestorinc.controller.abstracts.IInteractionLogManager;
import com.gestorinc.controller.model.AbstractRestControllerResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.repository.IInterfaceLogRepository;
import com.gestorinc.repository.entity.LogInterfaz;
import com.gestorinc.security.CustomRequestWrapper;
import com.gestorinc.service.dto.AbstractServiceResponseDTO;
import com.gestorinc.service.dto.AuditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.gestorinc.utils.Constants.OBJECT_MAPPER;

@Component
public class InteractionLogManager implements IInteractionLogManager {

    @Autowired
    private IInterfaceLogRepository interfaceLogRepository;
    @Autowired
    private HttpServletRequest request;

    private CustomRequestWrapper currentRequestWrapper;

    @Override
    public void generateAuditLog(AbstractRestControllerResponse restControllerResponse,
                                 AbstractServiceResponseDTO abstractServiceResponseDTO,
                                 String message) throws IOException {
        currentRequestWrapper = new CustomRequestWrapper(request);
        saveLog(AuditDTO.builder()
                .user(currentRequestWrapper.authenticatedBank())
                .product(abstractServiceResponseDTO.getProductCodeAud())
                .participant(abstractServiceResponseDTO.getParticipantAccountAud())
                .operation(currentRequestWrapper.operationURL())
                .ip(currentRequestWrapper.clientIpAddress())
                .jsonRequestFrame(currentRequestWrapper.getBody())
                .jsonResponseFrame(OBJECT_MAPPER.writeValueAsString(restControllerResponse))
                .status(AuditDTO.Status.OK)
                .message(message)
                .build());
    }

    @Override
    public void generateAuditLogError(ErrorRestControllerResponse restControllerResponse,
                                      String message) throws IOException {
        currentRequestWrapper = new CustomRequestWrapper(request);
        saveLog(AuditDTO.builder()
                .user(currentRequestWrapper.authenticatedBank())
                .operation(currentRequestWrapper.operationURL())
                .ip(currentRequestWrapper.clientIpAddress())
                .jsonRequestFrame(currentRequestWrapper.getBody())
                .jsonResponseFrame(OBJECT_MAPPER.writeValueAsString(restControllerResponse))
                .status(AuditDTO.Status.ER)
                .message(message)
                .build());
    }

    private synchronized void saveLog(AuditDTO auditDTO) {

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
