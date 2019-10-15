package com.gestorinc.controller;

import com.gestorinc.controller.model.ContributionConfirmationRequest;
import com.gestorinc.controller.model.ContributionConfirmationRestControllerResponse;
import com.gestorinc.controller.model.ErrorRestControllerResponse;
import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.repository.entity.LogInterfaz;
import com.gestorinc.repository.entity.NotificacionAporte;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.EntityNotFoundException;

import static com.gestorinc.controller.model.enums.OperationEndpoint.CONTRIBUTION_CONFIRMATION;
import static com.gestorinc.repository.entity.enums.EnumEstadoIntencionAporte.RES;
import static com.gestorinc.repository.entity.enums.EnumEstadoNotificacionAporte.CNF;
import static com.gestorinc.utils.Constants.*;
import static com.gestorinc.utils.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContributionConfirmationControllerTest extends AbstractControllerTest {

    @Test
    public void confirmContribution_withContributionIntention()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_CONFIRMATION,
                CONTRIBUTION_CONFIRMATION_NPE_REST_CONTROLLER_REQUEST);

        validateResponse(result);
        validateUpdatedStatusContributionConfirmation_11111111111111111111111111111111117();
        validateUpdatedStatusContributionIntention_11111111111111111111111111111111117();
        validateSavedLogContributionConfirmation_11111111111111111111111111111111117();
    }

    private void validateResponse(MvcResult result) throws java.io.IOException {
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(),
                ContributionConfirmationRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(
                        ContributionConfirmationRestControllerResponse.builder()
                                .respuesta(OK)
                                .build());
    }

    private void validateSavedLogContributionConfirmation_11111111111111111111111111111111117() {
        LogInterfaz logInterfaz =
                interfaceLogRepository.findOne(1l);
        assertThat(LOG_INTERFACE_CONTRIBUTION_CONFIRMATION_NPE_11111111111111111111111111111111117)
                .isEqualToIgnoringGivenFields(logInterfaz, FECHA_HORA_REGISTRO);
    }

    private void validateUpdatedStatusContributionIntention_11111111111111111111111111111111117() {
        IntencionAporte intencionAporte =
                contributionIntentionRepository.findByNPE(_11111111111111111111111111111111117)
                        .orElseThrow(EntityNotFoundException::new);
        assertThat(intencionAporte.getEstado())
                .isEqualTo(RES);
    }

    private void validateUpdatedStatusContributionConfirmation_11111111111111111111111111111111117() {
        NotificacionAporte notificacionAporte =
                contributionNotificationRepository.findByNPE(_11111111111111111111111111111111117)
                        .orElseThrow(EntityNotFoundException::new);
        assertThat(notificacionAporte.getEstado())
                .isEqualTo(CNF);
        assertThat(notificacionAporte.getReferencia())
                .isEqualTo(BANK_REFERENCE_XXXXXXX);
    }

    @Test
    public void confirmContribution_withoutContributionIntention()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_CONFIRMATION,
                CONTRIBUTION_CONFIRMATION_2_REST_CONTROLLER_REQUEST);

        validateResponse(result);
        validateUpdatedStatusContributionConfirmation_2();
        validateSavedLogContributionConfirmation_2();
    }

    private void validateErrorResponse(MvcResult result) throws java.io.IOException {
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(),
                ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(
                        ERROR_10_RESPONSE);
    }

    private void validateUpdatedStatusContributionConfirmation_2() {
        NotificacionAporte notificacionAporte =
                contributionNotificationRepository.findBySecNotificacion(2l)
                        .orElseThrow(EntityNotFoundException::new);
        assertThat(notificacionAporte.getEstado())
                .isEqualTo(CNF);
        assertThat(notificacionAporte.getReferencia())
                .isEqualTo(BANK_REFERENCE_XXXXXXX);
    }

    private void validateSavedLogContributionConfirmation_2() {
        LogInterfaz logInterfaz =
                interfaceLogRepository.findOne(1l);
        assertThat(LOG_INTERFACE_CONTRIBUTION_CONFIRMATION_2)
                .isEqualToIgnoringGivenFields(logInterfaz, FECHA_HORA_REGISTRO);
    }

    @Test
    public void confirmContribution_ContributionNotificationNotFound()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_CONFIRMATION,
                CONTRIBUTION_CONFIRMATION_3_REST_CONTROLLER_REQUEST);

        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(),
                ErrorRestControllerResponse.class))
        .isEqualToComparingFieldByFieldRecursively(ERROR_17_RESPONSE);
    }

    @Test
    public void confirmContribution_withoutBankReference()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_CONFIRMATION,
                new ContributionConfirmationRequest(1l, null));

        validateErrorResponse(result);
    }

    @Test
    public void confirmContribution_ConfirmedContributionNotification()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_CONFIRMATION,
                CONTRIBUTION_CONFIRMATION_4_REST_CONTROLLER_REQUEST);

        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(),
                ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_21_RESPONSE);
    }

    @Test
    public void confirmContribution_ProcessedContributionNotification()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_CONFIRMATION,
                CONTRIBUTION_CONFIRMATION_5_REST_CONTROLLER_REQUEST);

        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(),
                ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_22_RESPONSE);
    }
}