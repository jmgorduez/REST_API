package com.gestorinc.service.abstractions;

import com.gestorinc.service.dto.AuditDTO;

public interface ILogManager {
    void guardarLog(AuditDTO auditDTO);
}
