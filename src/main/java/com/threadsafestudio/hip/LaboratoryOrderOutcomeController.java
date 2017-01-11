package com.threadsafestudio.hip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.GetLaboratoryOrderOutcomeBuilder;
import se.hip.sdk.api.GetReferralOutcomeBuilder;
import se.hip.sdk.api.core.DefaultSubjectOfCare;
import se.hip.sdk.api.core.Response;
import se.hip.sdk.api.laboratory.LaboratoryOrderOutcome;
import se.hip.sdk.api.operation.GetLaboratoryOrderOutcome;
import se.hip.sdk.api.operation.GetReferralOutcome;
import se.hip.sdk.api.query.result.DataResultSet;
import se.hip.sdk.api.referraloutcome.ReferralOutcome;

/**
 * Created by pascal on 2017-01-10.
 */
@Controller
public class LaboratoryOrderOutcomeController {
    @Autowired
    private Api api;

    @Autowired
    ConsentService consentService;

    @RequestMapping("/laboratoryorder")
    @ResponseBody
    public Response<DataResultSet<LaboratoryOrderOutcome>> careDocumentation() {
        boolean consentPosted = consentService.postConsent();
        if (consentPosted) {
            final GetLaboratoryOrderOutcome request =
                    api.getOperationBuilder(GetLaboratoryOrderOutcomeBuilder.class)
                        .as(consentService.getCareActor())
                        .withSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                        .build();
            return request.execute();
        } else {
            return null;
        }
    }
}
