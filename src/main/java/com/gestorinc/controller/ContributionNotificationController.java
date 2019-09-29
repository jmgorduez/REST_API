package com.gestorinc.controller;

import com.gestorinc.controller.model.ContributionNotificationRestControllerRequest;
import com.gestorinc.controller.model.ContributionNotificationRestControllerResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.gestorinc.utils.Constants.*;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class ContributionNotificationController {

    @ApiOperation(value = REGISTRA_UNA_NOTIFICACION_DE_APORTE,
            response = ContributionNotificationRestControllerRequest.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = CREADA_NOTIFICACIÓN_DE_APORTE),
            @ApiResponse(code = 401, message = DEBE_AUTENTICARSE_USANDO_AUTENTICAR_Y_ENVIAR_EL_TOKEN_RECIBIDO_VIA_HEADER_AUTHENTICATION_BEARER_TOKEN),
            @ApiResponse(code = 400, message = PARAMETROS_INCORRECTOS),
            @ApiResponse(code = 500, message = REGLA_DE_NEGOCIO_NO_CUMPLIDA_CONSULTA_NOTIFICACION)
    })
    @ApiImplicitParam(name = TOKEN, value = TOKEN_DE_ACCESO_OBTENIDO_LUEGO_DE_AUTENTICARSE, required = true, dataType = STRING,
            paramType = AUTHENTICATION_HEADER_PARAM)
    @PutMapping(produces = APPLICATION_JSON, path = V1_NOTIFICAR_APORTE)
    @ResponseStatus(ACCEPTED)
    public ResponseEntity<ContributionNotificationRestControllerResponse> contributionNotification(
            @NotNull @RequestBody @ApiParam(value = ENTRADA_PARA_LA_NOTIFICACIÓN_DE_APORTE)
            final ContributionNotificationRestControllerRequest contributionNotificationRequest) {

        if (contributionNotificationRequest.getTipoIdentificador().equals(NPE)) {
            return new ResponseEntity<ContributionNotificationRestControllerResponse> ( ContributionNotificationRestControllerResponse.builder()
                    .correlativo(1l)
                    .respuesta(OK)
                    .build(), CREATED);
        } else {
            return new ResponseEntity<ContributionNotificationRestControllerResponse> ( ContributionNotificationRestControllerResponse.builder()
                    .correlativo(2l)
                    .respuesta(OK)
                    .build(), CREATED);
        }
    }
}
