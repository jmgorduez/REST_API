package com.gestorinc.controller;

import com.gestorinc.controller.model.ContributionConfirmationRequest;
import com.gestorinc.controller.model.ContributionConfirmationRestControllerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.gestorinc.utils.Constants.APPLICATION_JSON;
import static com.gestorinc.utils.Constants.V1_CONFIRMAR_APORTE;

@RestController
public class ContributionConfirmationController {

    @PutMapping(produces = APPLICATION_JSON, path = V1_CONFIRMAR_APORTE)
    public ResponseEntity<ContributionConfirmationRestControllerResponse> contributionConfirmation(@NotNull @RequestBody final ContributionConfirmationRequest contributionConfirmationRequest) {
        return null;
    }
}
