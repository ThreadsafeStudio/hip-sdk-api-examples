package com.threadsafestudio.hip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.GetCareDocumentationBuilder;
import se.hip.sdk.api.GetImagingOutcomeBuilder;
import se.hip.sdk.api.caredocumentation.CareDocumentation;
import se.hip.sdk.api.core.*;
import se.hip.sdk.api.imagingoutcome.ImagingOutcome;
import se.hip.sdk.api.operation.GetCareDocumentation;
import se.hip.sdk.api.operation.GetImagingOutcome;
import se.hip.sdk.api.query.result.DataResultSet;

/**
 * Created by pascal on 2017-01-10.
 */
@Controller
public class ImagingOutcomeController {
    @Autowired
    private Api api;

    @Autowired
    ConsentService consentService;

    @RequestMapping("/imagingoutcome")
    @ResponseBody
    public Response<DataResultSet<ImagingOutcome>> careDocumentation() {
        boolean consentPosted = consentService.postConsent();
        if (consentPosted) {
            final GetImagingOutcome request =
                    api.getOperationBuilder(GetImagingOutcomeBuilder.class)
                        .as(consentService.getCareActor())
                        .withSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                        .build();
            return request.execute();
        } else {
            return null;
        }
    }
}
