package com.gestorinc.controller;

import com.gestorinc.controller.model.ContributionConfirmationRequest;
import com.gestorinc.controller.model.ContributionConfirmationRestControllerResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.gestorinc.utils.Constants.*;
import static org.springframework.http.HttpStatus.ACCEPTED;

@Api(PAGO_APORTES)
@RestController
public class ContributionConfirmationController {

    @ApiOperation(value = CONFIRMAR_EL_PAGO_DE_LA_NOTIFICACIÓN_DE_APORTE,
            response = ContributionConfirmationRestControllerResponse.class,
            tags = PAGO_APORTES)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = CONFIRMADA_LA_NOTIFICACIÓN_DE_APORTE,
                    response = ContributionConfirmationRestControllerResponse.class),
            @ApiResponse(code = 401,
                    message = DEBE_AUTENTICARSE_USANDO_AUTENTICAR_Y_ENVIAR_EL_TOKEN_RECIBIDO_VIA_HEADER_AUTHENTICATION_BEARER_TOKEN,
                    response = ErrorRestControllerResponse.class),
            @ApiResponse(code = 400, message = PARAMETROS_INCORRECTOS,
                    response = ErrorRestControllerResponse.class),
            @ApiResponse(code = 500, message = REGLA_DE_NEGOCIO_NO_CUMPLIDA_CONFIRMACION,
                    response = ErrorRestControllerResponse.class)
    })
    @PutMapping(produces = APPLICATION_JSON, path = V1_CONFIRMAR_APORTE)
    @ResponseStatus(ACCEPTED)
    public ResponseEntity<ContributionConfirmationRestControllerResponse> contributionConfirmation(
            @NotNull @Valid @RequestBody @ApiParam(value = ENTRADA_PARA_LA_CONFIRMACIÓN_DE_APORTE)
            final ContributionConfirmationRequest contributionConfirmationRequest) {
        return new ResponseEntity<ContributionConfirmationRestControllerResponse>(
                ContributionConfirmationRestControllerResponse.builder()
                        .respuesta(OK)
                        .build(), ACCEPTED);

    }
}
