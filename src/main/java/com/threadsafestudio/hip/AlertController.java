package com.threadsafestudio.hip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.GetAlertInformationBuilder;
import se.hip.sdk.api.core.DefaultSubjectOfCare;
import se.hip.sdk.api.core.Response;
import se.hip.sdk.api.operation.GetAlertInformation;
import se.hip.sdk.api.query.result.DataResultSet;
import se.hip.sdk.service.api.impl.AlertInformationImpl;

/**
 * Created by pascal on 2017-01-10.
 */
@Controller
public class AlertController {
    @Autowired
    private Api api;

    @Autowired
    ConsentService consentService;

    @RequestMapping("/alert")
    @ResponseBody
    public Response<DataResultSet<AlertInformationImpl>> careDocumentation() {
        boolean consentPosted = consentService.postConsent();
        if (consentPosted) {
            final GetAlertInformation request =
                    api.getOperationBuilder(GetAlertInformationBuilder.class)
                        .as(consentService.getCareActor())
                        .withSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                        .build();
            return request.execute();
        } else {
            return null;
        }
    }
}
