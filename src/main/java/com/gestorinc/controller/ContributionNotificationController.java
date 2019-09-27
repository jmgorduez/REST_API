package com.gestorinc.controller;

import com.gestorinc.controller.model.ContributionNotificationRestControllerRequest;
import com.gestorinc.controller.model.ContributionNotificationRestControllerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.gestorinc.utils.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ContributionNotificationController {

    @PutMapping(produces = APPLICATION_JSON, path = V1_NOTIFICAR_APORTE)
    public ResponseEntity<ContributionNotificationRestControllerResponse> contributionNotification(@NotNull @RequestBody final ContributionNotificationRestControllerRequest contributionNotificationRequest) {

        if (contributionNotificationRequest.getTipoIdentificador().equals(NPE)) {
            return ok(ContributionNotificationRestControllerResponse.builder()
                    .correlativo(1l)
                    .respuesta(OK)
                    .build());
        } else {
            return ok(ContributionNotificationRestControllerResponse.builder()
                    .correlativo(2l)
                    .respuesta(OK)
                    .build());
        }
    }
}
