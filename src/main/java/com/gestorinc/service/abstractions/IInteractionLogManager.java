package com.gestorinc.service.abstractions;

import com.gestorinc.controller.model.AbstractRestControllerResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.service.dto.AbstractServiceResponseDTO;

import java.io.IOException;

public interface IInteractionLogManager {
    void generateAuditLog(AbstractRestControllerResponse restControllerResponse,
                          AbstractServiceResponseDTO abstractServiceResponseDTO,
                          String message) throws IOException;
    void generateAuditLogError(ErrorRestControllerResponse restControllerResponse,
                               String message) throws IOException;
}
