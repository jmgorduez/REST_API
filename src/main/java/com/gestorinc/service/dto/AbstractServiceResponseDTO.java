package com.gestorinc.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractServiceResponseDTO {

    protected String participantAccountAud;
    protected String productCodeAud;
}
