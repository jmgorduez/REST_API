package com.gestorinc.controller.model.enums;

import lombok.Getter;

import static com.gestorinc.utils.Constants.*;

@Getter
public enum OperationEndpoint {
    CLIENT_QUERY(V1_CONSULTAR_CLIENTE),
    CONTRIBUTION_NOTIFICATION(V1_NOTIFICAR_APORTE),
    CONTRIBUTION_CONFIRMATION(V1_CONFIRMAR_APORTE),
    AUTH_SIGNIN(AUTENTICACION),
    AUTHENTICATED_USER(USUARIO_AUTENTICADO);

    private final String url;

    OperationEndpoint(String url) {
        this.url = url;
    }
}
