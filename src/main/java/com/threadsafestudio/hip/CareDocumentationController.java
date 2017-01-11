package com.threadsafestudio.hip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.GetCareDocumentationBuilder;
import se.hip.sdk.api.caredocumentation.CareDocumentation;
import se.hip.sdk.api.core.*;
import se.hip.sdk.api.operation.GetCareDocumentation;
import se.hip.sdk.api.query.result.DataResultSet;

/**
 * Created by pascal on 2017-01-10.
 */
@Controller
public class CareDocumentationController {
    @Autowired
    private Api api;

    @Autowired
    ConsentService consentService;

    @RequestMapping("/caredocumentation")
    @ResponseBody
    public Response<DataResultSet<CareDocumentation>> careDocumentation() {
        boolean consentPosted = consentService.postConsent();
        if (consentPosted) {
            final GetCareDocumentation request = api.getOperationBuilder(GetCareDocumentationBuilder.class)
                    .as(consentService.getCareActor())
                    .withSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                    .build();
            return request.execute();
        } else {
            return null;
        }
    }
}
