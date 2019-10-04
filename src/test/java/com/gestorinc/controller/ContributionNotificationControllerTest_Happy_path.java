package com.gestorinc.controller;

import com.gestorinc.controller.model.ContributionNotificationRestControllerResponse;
import com.gestorinc.repository.entity.IntencionAporte;
import com.gestorinc.repository.entity.LogInterfaz;
import com.gestorinc.repository.entity.NotificacionAporte;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import static com.gestorinc.controller.model.enums.OperationEndpoint.CONTRIBUTION_NOTIFICATION;
import static com.gestorinc.repository.entity.enums.EnumEstadoIntencionAporte.NTF;
import static com.gestorinc.utils.Constants.OBJECT_MAPPER;
import static com.gestorinc.utils.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql({"/schema-h2.sql", "/data-h2.sql"})
public class ContributionNotificationControllerTest_Happy_path extends AbstractControllerTest {

    @Test
    public void contributionNotification_should_create_a_contribution_notification_for_contribution_intention_NPE()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_NOTIFICATION,
                CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111111_REST_CONTROLLER_REQUEST);

        validateResponse(result);
        validateCreatedContributionNotificationByNPE();
        validateUpdatedContributionIntentionByNPEToNotifiedStatus();
        validateSavedLogForContributionNotificationByNPE();
    }

    private void validateSavedLogForContributionNotificationByNPE() {
        LogInterfaz logInterfaz =
                interfaceLogRepository.findOne(1l);
        assertThat(logInterfaz)
                .isEqualToIgnoringGivenFields(
                        LOG_INTERFACE_CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111111,
                        FECHA_HORA_REGISTRO);
    }

    private void validateUpdatedContributionIntentionByNPEToNotifiedStatus() {
        IntencionAporte intencionAporte =
                contributionIntentionRepository.findByNPE(_11111111111111111111111111111111111).get();
        assertThat(intencionAporte.getEstado()).isEqualTo(NTF);
    }

    private void validateCreatedContributionNotificationByNPE() {
        NotificacionAporte notificacionAporte =
                contributionNotificationRepository.findByNPE(_11111111111111111111111111111111111)
                        .orElseThrow(IllegalStateException::new);
        assertThat(notificacionAporte)
                .isEqualToIgnoringGivenFields(
                        CONTRIBUTION_NOTIFICATION_11111111111111111111111111111111111, PK, FECHA_HORA_REGISTRO, FECHA_HORA_APORTE);
    }

    private void validateResponse(MvcResult result) throws java.io.IOException {
        assertThat(result.getResponse().getStatus())
                .isEqualTo(CREATED.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(),
                ContributionNotificationRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(CONTRIBUTION_NOTIFICATION_REST_CONTROLLER_RESPONSE_1);
    }

    @Test
    public void contributionNotification_should_create_a_contribution_notification_for_clientId()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_NOTIFICATION,
                CONTRIBUTION_NOTIFICATION_CLIENT_ID_12345678910_REST_CONTROLLER_REQUEST);

        validateResponse(result);
        validateCreatedContributionNotificationByClientId();
        validateSavedLogForContributionNotificationByClientId();
    }

    private void validateCreatedContributionNotificationByClientId() {
        NotificacionAporte notificacionAporte =
                contributionNotificationRepository.findBySecNotificacion(3l)
                        .orElseThrow(IllegalStateException::new);
        assertThat(notificacionAporte)
                .isEqualToIgnoringGivenFields(
                        CONTRIBUTION_NOTIFICATION_12345678910, FECHA_HORA_REGISTRO, FECHA_HORA_APORTE);
    }

    private void validateSavedLogForContributionNotificationByClientId() {
        LogInterfaz logInterfaz =
                interfaceLogRepository.findOne(1l);
        assertThat(LOG_INTERFACE_CONTRIBUTION_NOTIFICATION_CLIENT_ID_12345678910)
                .isEqualToIgnoringGivenFields(logInterfaz, FECHA_HORA_REGISTRO);
    }
}