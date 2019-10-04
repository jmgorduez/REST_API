package com.gestorinc.controller;

import com.gestorinc.controller.model.ErrorRestControllerResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import static com.gestorinc.controller.model.enums.OperationEndpoint.CONTRIBUTION_NOTIFICATION;
import static com.gestorinc.utils.Constants.*;
import static com.gestorinc.utils.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
@Sql({"/schema-test.sql", "/data-test.sql"})
public class ContributionNotificationControllerTest_Exceptional_Condition_Client_Id
        extends AbstractControllerTest {

    @Test
    public void contributionNotificationByClientId_RequestWithoutAmount_should_return_ER_10()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_NOTIFICATION,
                CONTRIBUTION_NOTIFICATION_CLIENT_ID_WITHOUT_AMOUNT_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_10_RESPONSE);
    }

    @Test
    public void contributionNotificationByClientId_RequestWithoutParticipantAccount_should_return_ER_10()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_NOTIFICATION,
                CONTRIBUTION_NOTIFICATION_CLIENT_ID_WITHOUT_PARTICIPANT_ACCOUNT_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_10_RESPONSE);
    }

    @Test
    public void contributionNotificationByClientId_RequestWithoutGLNCode_should_return_ER_10()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_NOTIFICATION,
                CONTRIBUTION_NOTIFICATION_CLIENT_ID_WITHOUT_GLN_CODE_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_10_RESPONSE);
    }

    @Test
    public void contributionNotificationByClientId_should_return_ER_4()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_NOTIFICATION,
                CONTRIBUTION_NOTIFICATION_CLIENT_ID_UNREGISTERED_PERSON_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_4_RESPONSE);
    }

    @Test
    public void contributionNotificationByClientId_should_return_ER_5()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_NOTIFICATION,
                CONTRIBUTION_NOTIFICATION_CLIENT_ID_INACTIVE_CLIENT_ACCOUNT_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_5_RESPONSE);
    }

    @Test
    public void contributionNotificationByClientId_should_return_ER_9()
            throws Exception {

        MvcResult result = executePutRestInteraction(CONTRIBUTION_NOTIFICATION,
                CONTRIBUTION_NOTIFICATION_CLIENT_ID_LOCAL_PERSON_WITHOUT_DUI_REST_CONTROLLER_REQUEST);

        assertThat(result.getResponse().getStatus())
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorRestControllerResponse.class))
                .isEqualToComparingFieldByFieldRecursively(ERROR_9_RESPONSE);
    }
}