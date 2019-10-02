package com.gestorinc.service.abstractions;

import com.gestorinc.controller.model.AbstractRestControllerResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.service.dto.AbstractServiceResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.function.Supplier;

public interface IInteractionLogManager {
    void generateAuditLog(HttpServletRequest httpServletRequest,
                          AbstractRestControllerResponse restControllerResponse,
                          AbstractServiceResponseDTO abstractServiceResponseDTO,
                          String message) throws IOException;

    void generateAuditLogError(HttpServletRequest httpServletRequest,
                               ErrorRestControllerResponse restControllerResponse,
                               String message) throws IOException;
}
