package com.gestorinc.controller;

import com.gestorinc.controller.model.AbstractRestControllerResponse;
import com.gestorinc.repository.IInterfaceLogRepository;
import com.gestorinc.repository.entity.LogInterfaz;
import com.gestorinc.controller.abstracts.IInteractionLogManager;
import com.gestorinc.service.dto.AbstractServiceResponseDTO;
import com.gestorinc.service.dto.AuditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

import static com.gestorinc.utils.Constants.*;
import static java.util.Optional.ofNullable;

@Component
public class InteractionLogManager implements IInteractionLogManager {

    @Autowired
    private IInterfaceLogRepository interfaceLogRepository;
    @Autowired
    protected HttpServletRequest request;

    @Override
    public void generateAuditLog(AbstractRestControllerResponse abstractRestControllerResponse,
                                    AbstractServiceResponseDTO abstractServiceResponseDTO,
                                    String message) throws IOException {
        saveLog(AuditDTO.builder()
                .user(authenticatedBank())
                .product(abstractServiceResponseDTO.getProductCodeAud())
                .participant(abstractServiceResponseDTO.getParticipantAccountAud())
                .operation(operationURL())
                .ip(clientIpAddress())
                .jsonRequestFrame(request.getReader().lines().collect(Collectors.joining()))
                .jsonResponseFrame(OBJECT_MAPPER.writeValueAsString(abstractRestControllerResponse))
                .status(AuditDTO.Status.OK)
                .message(message)
                .build());
    }

    @Override
    public void generateAuditLogError(AbstractRestControllerResponse abstractRestControllerResponse,
                                         String message) throws IOException {

        saveLog(AuditDTO.builder()
                .user(authenticatedBank())
                .operation(operationURL())
                .ip(clientIpAddress())
                .jsonRequestFrame(request.getReader().lines().collect(Collectors.joining()))
                .jsonResponseFrame(OBJECT_MAPPER.writeValueAsString(abstractRestControllerResponse))
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

    private String operationURL() {
        return request.getRequestURI();
    }

    private String authenticatedBank() {

        if (ofNullable(request.getUserPrincipal()).isPresent()) {
            return request.getUserPrincipal().getName();
        }
        return ANONYMOUS;
    }

    private String clientIpAddress() {
        return request.getRemoteAddr();
    }
}
