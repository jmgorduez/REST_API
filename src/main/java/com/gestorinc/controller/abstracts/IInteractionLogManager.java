package com.gestorinc.controller.abstracts;

import com.gestorinc.controller.model.AbstractRestControllerResponse;
import com.gestorinc.service.dto.AbstractServiceResponseDTO;

import java.io.IOException;

public interface IInteractionLogManager {
    void generateAuditLog(AbstractRestControllerResponse abstractRestControllerResponse,
                          AbstractServiceResponseDTO abstractServiceResponseDTO,
                          String message) throws IOException;
    void generateAuditLogError(AbstractRestControllerResponse abstractRestControllerResponse,
                               String message) throws IOException;
}
