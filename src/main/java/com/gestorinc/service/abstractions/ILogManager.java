package com.gestorinc.service.abstractions;

import com.gestorinc.service.dto.AuditoriaDTO;

public interface ILogManager {
    void guardarLog(AuditoriaDTO auditoriaDTO);
}
