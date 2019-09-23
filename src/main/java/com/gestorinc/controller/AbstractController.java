package com.gestorinc.controller;

import com.gestorinc.controller.model.AbstractRestControllerResponse;
import com.gestorinc.service.abstractions.ILogManager;
import com.gestorinc.service.dto.AbstractServiceResponseDTO;
import com.gestorinc.service.dto.AuditDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

import static com.gestorinc.utils.Constants.OBJECT_MAPPER;

public abstract class AbstractController {

    @Autowired
    protected ILogManager logManager;
    @Autowired
    protected HttpServletRequest request;

    protected void generateAuditLog(AbstractRestControllerResponse abstractRestControllerResponse,
                                    AbstractServiceResponseDTO abstractServiceResponseDTO,
                                    String message) throws IOException {
        logManager.saveLog(AuditDTO.builder()
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

    protected void generateAuditLogError(AbstractRestControllerResponse abstractRestControllerResponse,
                                         String message) throws IOException {

        logManager.saveLog(AuditDTO.builder()
                .user(authenticatedBank())
                .operation(operationURL())
                .ip(clientIpAddress())
                .jsonRequestFrame(request.getReader().lines().collect(Collectors.joining()))
                .jsonResponseFrame(OBJECT_MAPPER.writeValueAsString(abstractRestControllerResponse))
                .status(AuditDTO.Status.ER)
                .message(message)
                .build());
    }

    private String operationURL() {
        return request.getRequestURI();
    }

    private String authenticatedBank() {
        return request.getUserPrincipal()
                .getName();
    }

    private String clientIpAddress() {
        return request.getRemoteAddr();
    }
}
