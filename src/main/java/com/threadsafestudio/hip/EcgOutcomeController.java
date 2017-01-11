package com.threadsafestudio.hip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.GetECGOutcomeBuilder;
import se.hip.sdk.api.GetImagingOutcomeBuilder;
import se.hip.sdk.api.core.DefaultSubjectOfCare;
import se.hip.sdk.api.core.Response;
import se.hip.sdk.api.ecgoutcome.ECGOutcome;
import se.hip.sdk.api.imagingoutcome.ImagingOutcome;
import se.hip.sdk.api.operation.GetECGOutcome;
import se.hip.sdk.api.operation.GetImagingOutcome;
import se.hip.sdk.api.query.result.DataResultSet;

/**
 * Created by pascal on 2017-01-10.
 */
@Controller
public class EcgOutcomeController {
    @Autowired
    private Api api;

    @Autowired
    ConsentService consentService;

    @RequestMapping("/ecgoutcome")
    @ResponseBody
    public Response<DataResultSet<ECGOutcome>> careDocumentation() {
        boolean consentPosted = consentService.postConsent();
        if (consentPosted) {
            final GetECGOutcome request =
                    api.getOperationBuilder(GetECGOutcomeBuilder.class)
                        .as(consentService.getCareActor())
                        .withSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                        .build();
            return request.execute();
        } else {
            return null;
        }
    }
}
