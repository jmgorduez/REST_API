package com.gestorinc.controller;

import com.gestorinc.controller.model.ClientQueryRestControllerRequest;
import com.gestorinc.controller.model.ClientQueryRestControllerResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.service.abstractions.IClientQueryService;
import com.gestorinc.service.abstractions.IDTOMapper;
import com.gestorinc.service.abstractions.IInteractionLogManager;
import com.gestorinc.service.dto.ClientQueryClientIdServiceResponseDTO;
import com.gestorinc.service.dto.ClientQueryNPEServiceResponseDTO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

import static com.gestorinc.utils.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@Api(PAGO_APORTES)
@RestController
public class ClientQueryController {

    @Autowired
    private IClientQueryService clientQueryService;
    @Autowired
    private IInteractionLogManager logManager;
    @Autowired
    private IDTOMapper dtoMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @ApiOperation(value = CONSULTAR_CLIENTE, response = ClientQueryRestControllerResponse.class,
            notes = MUESTRA_INFORMACIÓN_DEL_NPE_O_DE_LAS_CUENTAS_DEL_PARTICIPANTE_EN_DEPENDENCIA_DEL_TIPO_IDENTIFICADOR_RECIBIDO,
            tags = PAGO_APORTES)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = INFORMACIÓN_DEL_NPE_O_LA_CUENTA_DEL_PARTICIPANTE_ENCONTRADA,
                    response = ClientQueryRestControllerResponse.class),
            @ApiResponse(code = 401,
                    message = DEBE_AUTENTICARSE_USANDO_AUTENTICAR_Y_ENVIAR_EL_TOKEN_RECIBIDO_VIA_HEADER_AUTHENTICATION_BEARER_TOKEN,
                    response = ErrorRestControllerResponse.class),
            @ApiResponse(code = 400, message = PARAMETROS_INCORRECTOS,
                    response = ErrorRestControllerResponse.class),
            @ApiResponse(code = 500, message = REGLA_DE_NEGOCIO_NO_CUMPLIDA_CONSULTA_NOTIFICACION,
                    response = ErrorRestControllerResponse.class)
    })
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE,
            path = V1_CONSULTAR_CLIENTE)
    public ResponseEntity<ClientQueryRestControllerResponse> clientQuery(
            @NotNull @Valid @RequestBody @ApiParam(value = ENTRADA_PARA_LA_CONSULTA_DE_CLIENTE)
            final ClientQueryRestControllerRequest clientQueryRequest)
            throws IOException, MissingServletRequestParameterException {

        if (isByNPE(clientQueryRequest)) {

            return ok(clientQueryByNPE(clientQueryRequest));
        } else if (isByClientId(clientQueryRequest)) {

            return ok(clientQueryByClientId(clientQueryRequest));
        }

        throw new MissingServletRequestParameterException(TIPO_DE_IDENTIFICADOR, String.class.getName());
    }

    private boolean isByNPE(ClientQueryRestControllerRequest request) {
        return request.getTipoIdentificador().equals(NPE);
    }

    private boolean isByClientId(ClientQueryRestControllerRequest request) {
        return request.getTipoIdentificador().equals(ID);
    }

    private ClientQueryRestControllerResponse clientQueryByClientId(ClientQueryRestControllerRequest clientQueryRequest) throws IOException {

        ClientQueryClientIdServiceResponseDTO clientQueryClientIdResponseDTO
                = clientQueryService.queryByClientId(clientQueryRequest.getIdentificador(), clientQueryRequest.getCodigoGLN());

        ClientQueryRestControllerResponse clientQueryResponse = dtoMapper.buildClientQueryResponse(clientQueryClientIdResponseDTO);

        logManager.generateAuditLog(httpServletRequest, clientQueryResponse,
                clientQueryClientIdResponseDTO, EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_ID_CLIENTE);

        return clientQueryResponse;
    }

    private ClientQueryRestControllerResponse clientQueryByNPE(ClientQueryRestControllerRequest clientQueryRequest) throws IOException {

        ClientQueryNPEServiceResponseDTO clientQueryNPEResponseDTO
                = clientQueryService.queryByNPE(clientQueryRequest.getIdentificador());

        ClientQueryRestControllerResponse clientQueryResponse = dtoMapper.buildClientQueryResponse(clientQueryNPEResponseDTO);

        logManager.generateAuditLog(httpServletRequest, clientQueryResponse,
                clientQueryNPEResponseDTO, EJECUCION_DE_CONSULTA_DE_CLIENTE_POR_NPE);

        return clientQueryResponse;
    }
}
