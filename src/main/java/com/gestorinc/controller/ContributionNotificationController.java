package com.gestorinc.controller;

import com.gestorinc.controller.model.ContributionNotificationRestControllerRequest;
import com.gestorinc.controller.model.ContributionNotificationRestControllerResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.security.CustomRequestWrapper;
import com.gestorinc.service.InteractionLogManager;
import com.gestorinc.service.abstractions.IContributionNotificationService;
import com.gestorinc.service.abstractions.IDTOMapper;
import com.gestorinc.service.dto.ContributionNotificationServiceResponseDTO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            @NotNull @Valid @RequestBody @ApiParam(value = ENTRADA_PARA_LA_NOTIFICACIÓN_DE_APORTE) final ContributionNotificationRestControllerRequest contributionNotificationRequest)
            throws IOException, MissingServletRequestParameterException, ParseException {
        CustomRequestWrapper customRequestWrapper = new CustomRequestWrapper(httpServletRequest);
        if (isByNPE(contributionNotificationRequest)) {
            return new ResponseEntity<ContributionNotificationRestControllerResponse>(
                    buildContributionNotificationByNPERestControllerResponse(
                            contributionNotificationRequest, customRequestWrapper), CREATED);
        } else if (isByClientId(contributionNotificationRequest)){
            validateMethodParams(contributionNotificationRequest);
            return new ResponseEntity<ContributionNotificationRestControllerResponse>(
                    buildContributionNotificationByClientIdRestControllerResponse(
                            contributionNotificationRequest, customRequestWrapper), CREATED);
        }

        throw new MissingServletRequestParameterException(TIPO_DE_IDENTIFICADOR, String.class.getName());
    }

    private boolean isByNPE(ContributionNotificationRestControllerRequest request) {
        return request.getTipoIdentificador().equals(NPE);
    }

    private boolean isByClientId(ContributionNotificationRestControllerRequest request) {
        return request.getTipoIdentificador().equals(ID);
    }

    private void validateMethodParams(ContributionNotificationRestControllerRequest contributionNotificationRequest)
            throws MissingServletRequestParameterException {
        if (contributionNotificationRequest.getCodigoGLN() == null) {
            throw new MissingServletRequestParameterException(CODIGO_GNL, Integer.class.getName());
        }
        if (StringUtils.isEmpty(contributionNotificationRequest.getCuentaAPV())) {
            throw new MissingServletRequestParameterException(CUENTA_APV, String.class.getName());
        }
        if (contributionNotificationRequest.getMonto() == null) {
            throw new MissingServletRequestParameterException(MONTO, String.class.getName());
        }
    }

    private ContributionNotificationRestControllerResponse buildContributionNotificationByClientIdRestControllerResponse(
            ContributionNotificationRestControllerRequest contributionNotificationRequest, CustomRequestWrapper customRequestWrapper)
            throws IOException, ParseException {

        ContributionNotificationServiceResponseDTO responseDTO =
                contributionNotificationService.contributionNotificationByClientId(
                        contributionNotificationRequest.getIdentificador(),
                        new SimpleDateFormat(DD_MM_YYYY_HH_MM)
                        .parse(contributionNotificationRequest.getFechaAporte()),
                        contributionNotificationRequest.getMedioPago().toString(),
                        contributionNotificationRequest.getCuentaAPV(),
                        contributionNotificationRequest.getMonto(),
                        contributionNotificationRequest.getCodigoGLN(),
                        customRequestWrapper.authenticatedBank());
        ContributionNotificationRestControllerResponse response =
                dtoMapper.buildContributionNotificationResponse(responseDTO);
        interactionLogManager.generateAuditLog(httpServletRequest, response,
                responseDTO, EJECUCIÓN_DE_NOTIFICACION_DE_APORTE_POR_IDENTIFICACION_DE_CLIENTE);
        return response;
    }

    private ContributionNotificationRestControllerResponse buildContributionNotificationByNPERestControllerResponse(
            ContributionNotificationRestControllerRequest contributionNotificationRequest, CustomRequestWrapper customRequestWrapper)
            throws IOException, ParseException {

        ContributionNotificationServiceResponseDTO responseDTO =
                contributionNotificationService
                        .contributionNotificationByNPE(
                                contributionNotificationRequest.getIdentificador(),
                                new SimpleDateFormat(DD_MM_YYYY_HH_MM)
                                        .parse(contributionNotificationRequest.getFechaAporte()),
                                contributionNotificationRequest.getMedioPago().toString(),
                                customRequestWrapper.authenticatedBank());
        ContributionNotificationRestControllerResponse response =
                dtoMapper.buildContributionNotificationResponse(responseDTO);
        interactionLogManager.generateAuditLog(httpServletRequest, response,
                responseDTO, EJECUCIÓN_DE_NOTIFICACION_DE_APORTE_POR_NPE);
        return response;
    }
}
