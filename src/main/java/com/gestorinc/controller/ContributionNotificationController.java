package com.gestorinc.controller;

import com.gestorinc.controller.model.ContributionNotificationRestControllerRequest;
import com.gestorinc.controller.model.ContributionNotificationRestControllerResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.service.InteractionLogManager;
import com.gestorinc.service.abstractions.IContributionNotificationService;
import com.gestorinc.service.abstractions.IDTOMapper;
import com.gestorinc.service.dto.ContributionNotificationServiceResponseDTO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.io.IOException;

import static com.gestorinc.utils.Constants.*;
import static org.springframework.http.HttpStatus.CREATED;

@Api(PAGO_APORTES)
@RestController
public class ContributionNotificationController {

    @Autowired
    private IContributionNotificationService contributionNotificationService;
    @Autowired
    private InteractionLogManager interactionLogManager;
    @Autowired
    private IDTOMapper dtoMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @ApiOperation(value = REGISTRAR_UNA_NOTIFICACION_DE_APORTE,
            response = ContributionNotificationRestControllerRequest.class,
            tags = PAGO_APORTES)
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = CREADA_NOTIFICACIÓN_DE_APORTE,
                    response = ContributionNotificationRestControllerResponse.class),
            @ApiResponse(code = 401,
                    message = DEBE_AUTENTICARSE_USANDO_AUTENTICAR_Y_ENVIAR_EL_TOKEN_RECIBIDO_VIA_HEADER_AUTHENTICATION_BEARER_TOKEN,
                    response = ErrorRestControllerResponse.class),
            @ApiResponse(code = 400,
                    message = PARAMETROS_INCORRECTOS,
                    response = ErrorRestControllerResponse.class),
            @ApiResponse(code = 500,
                    message = REGLA_DE_NEGOCIO_NO_CUMPLIDA_CONSULTA_NOTIFICACION,
                    response = ErrorRestControllerResponse.class)
    })
    @PutMapping(produces = APPLICATION_JSON, path = V1_NOTIFICAR_APORTE)
    @ResponseStatus(CREATED)
    public ResponseEntity<ContributionNotificationRestControllerResponse> contributionNotification(
            @NotNull @Valid @RequestBody @ApiParam(value = ENTRADA_PARA_LA_NOTIFICACIÓN_DE_APORTE) final ContributionNotificationRestControllerRequest contributionNotificationRequest) throws IOException {

        if (contributionNotificationRequest.getTipoIdentificador().equals(NPE)) {
            return new ResponseEntity<ContributionNotificationRestControllerResponse>(
                    buildContributionNotificationByNPERestControllerResponse(
                            contributionNotificationRequest), CREATED);
        } else {
            return new ResponseEntity<ContributionNotificationRestControllerResponse>(
                    buildContributionNotificationByClientIdRestControllerResponse(
                            contributionNotificationRequest), CREATED);
        }
    }

    private ContributionNotificationRestControllerResponse buildContributionNotificationByClientIdRestControllerResponse(
            ContributionNotificationRestControllerRequest contributionNotificationRequest)
            throws IOException {

        ContributionNotificationServiceResponseDTO responseDTO =
                contributionNotificationService.contributionNotificationByClientId(
                        contributionNotificationRequest.getIdentificador(),
                        contributionNotificationRequest.getFechaAporte(),
                        contributionNotificationRequest.getMedioPago().toString(),
                        contributionNotificationRequest.getCuentaAPV(),
                        contributionNotificationRequest.getMonto(),
                        contributionNotificationRequest.getCodigoGNL());
        ContributionNotificationRestControllerResponse response =
                dtoMapper.buildContributionNotificationResponse(responseDTO);
        interactionLogManager.generateAuditLog(httpServletRequest, response,
                responseDTO, EJECUCIÓN_DE_NOTIFICACION_DE_APORTE_POR_IDENTIFICACION_DE_CLIENTE);
        return response;
    }

    private ContributionNotificationRestControllerResponse buildContributionNotificationByNPERestControllerResponse(
            ContributionNotificationRestControllerRequest contributionNotificationRequest)
            throws IOException {

        ContributionNotificationServiceResponseDTO responseDTO =
                contributionNotificationService
                        .contributionNotificationByNPE(
                                contributionNotificationRequest.getIdentificador(),
                                contributionNotificationRequest.getFechaAporte(),
                                contributionNotificationRequest.getMedioPago().toString());
        ContributionNotificationRestControllerResponse response =
                dtoMapper.buildContributionNotificationResponse(responseDTO);
        interactionLogManager.generateAuditLog(httpServletRequest, response,
                responseDTO, EJECUCIÓN_DE_NOTIFICACION_DE_APORTE_POR_NPE);
        return response;
    }
}
