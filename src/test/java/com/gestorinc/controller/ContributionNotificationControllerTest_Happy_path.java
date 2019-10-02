package com.gestorinc.controller;

import com.gestorinc.controller.model.ContributionNotificationRestControllerResponse;
import com.gestorinc.repository.entity.LogInterfaz;
import com.gestorinc.repository.entity.NotificacionAporte;
import com.gestorinc.utils.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import static com.gestorinc.controller.model.enums.OperationEndpoint.CONTRIBUTION_NOTIFICATION;
import static com.gestorinc.utils.Constants.*;
import static com.gestorinc.utils.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql({"/schema-test.sql", "/data-test.sql"})
public class ContributionNotificationControllerTest_Happy_path extends AbstractControllerTest {

    @Test
    public void contributionNotification_should_create_a_contribution_notification_for_contribution_intention_NPE()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_NOTIFICATION,
                CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111111_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(CREATED.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(),
                ContributionNotificationRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(CONTRIBUTION_NOTIFICATION_REST_CONTROLLER_RESPONSE_1);

        NotificacionAporte notificacionAporte =
                contributionNotificationRepository
                        .findByNPE(_11111111111111111111111111111111111)
                        .orElseThrow(IllegalStateException::new);
        assertThat(notificacionAporte.getPk())
                .isEqualToComparingFieldByFieldRecursively(
                        CONTRIBUTION_NOTIFICATION_11111111111111111111111111111111111.getPk());
        assertThat(notificacionAporte)
                .isEqualToIgnoringGivenFields(
                        CONTRIBUTION_NOTIFICATION_11111111111111111111111111111111111,
                        PK, FECHA_HORA_REGISTRO, FECHA_HORA_APORTE);
    }

    @Test
    public void contributionNotification_should_create_a_contribution_notification_for_contribution_intention_NPE_log1()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_NOTIFICATION,
                CONTRIBUTION_NOTIFICATION_NPE_11111111111111111111111111111111111_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(CREATED.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(),
                ContributionNotificationRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(CONTRIBUTION_NOTIFICATION_REST_CONTROLLER_RESPONSE_1);

        LogInterfaz logInterfaz =
                interfaceLogRepository.findOne(1l);
        assertThat(logInterfaz)
                .isEqualToIgnoringGivenFields(logInterfaz, FECHA_HORA_REGISTRO);
    }
}