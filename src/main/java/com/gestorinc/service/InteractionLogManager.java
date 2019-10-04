package com.gestorinc.service;

import com.gestorinc.controller.model.AbstractRestControllerResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.repository.IInterfaceLogRepository;
import com.gestorinc.repository.entity.LogInterfaz;
import com.gestorinc.security.CustomRequestWrapper;
import com.gestorinc.service.abstractions.IInteractionLogManager;
import com.gestorinc.service.dto.AbstractServiceResponseDTO;
import com.gestorinc.service.dto.AuditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;

import static com.gestorinc.utils.Constants.OBJECT_MAPPER;

@Component
public class InteractionLogManager implements IInteractionLogManager {

    @Autowired
    private IInterfaceLogRepository interfaceLogRepository;

    @Override
    public void generateAuditLog(HttpServletRequest httpServletRequest,
                                 AbstractRestControllerResponse restControllerResponse,
                                 AbstractServiceResponseDTO abstractServiceResponseDTO,
                                 String message) throws IOException {
        CustomRequestWrapper currentRequestWrapper = new CustomRequestWrapper(httpServletRequest);
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
    public void generateAuditLogError(HttpServletRequest httpServletRequest,
                                      ErrorRestControllerResponse restControllerResponse,
                                      String message) throws IOException {
        CustomRequestWrapper currentRequestWrapper = new CustomRequestWrapper(httpServletRequest);
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

        Long autoIncrementedId =
                interfaceLogRepository.nextSequence().orElse(1L);

        LogInterfaz logInterfaz = LogInterfaz.builder()
                .id(autoIncrementedId)
                .fechaHoraRegistro(Calendar.getInstance().getTime())
                .usuario(auditDTO.getUser())
                .operacion(auditDTO.getOperation())
                .estado(LogInterfaz.EstadoLog.valueOf(auditDTO.getStatus().name()))
                .producto(auditDTO.getProduct())
                .participe(auditDTO.getParticipant())
                .tramaRequest(auditDTO.getJsonRequestFrame())
                .tramaResponse(auditDTO.getJsonResponseFrame())
                .mensaje(auditDTO.getMessage())
                .build();
        interfaceLogRepository.save(logInterfaz);
    }
}
