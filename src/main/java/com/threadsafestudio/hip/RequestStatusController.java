package com.threadsafestudio.hip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.GetMaternityMedicalHistoryBuilder;
import se.hip.sdk.api.GetRequestActivitiesBuilder;
import se.hip.sdk.api.core.DefaultSubjectOfCare;
import se.hip.sdk.api.core.Response;
import se.hip.sdk.api.maternity.Maternity;
import se.hip.sdk.api.operation.GetMaternityMedicalHistory;
import se.hip.sdk.api.operation.GetRequestActivities;
import se.hip.sdk.api.query.result.DataResultSet;
import se.hip.sdk.api.requestactivities.RequestActivity;

/**
 * Created by pascal on 2017-01-10.
 */
@Controller
public class RequestStatusController {
    @Autowired
    private Api api;

    @Autowired
    ConsentService consentService;

    @RequestMapping("/requeststatus")
    @ResponseBody
    public Response<DataResultSet<RequestActivity>> careDocumentation() {
        boolean consentPosted = consentService.postConsent();
        if (consentPosted) {
            final GetRequestActivities request =
                    api.getOperationBuilder(GetRequestActivitiesBuilder.class)
                        .as(consentService.getCareActor())
                        .withSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                        .build();
            return request.execute();
        } else {
            return null;
        }
    }
}
