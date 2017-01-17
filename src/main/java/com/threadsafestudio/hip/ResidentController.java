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
import se.hip.sdk.api.residents.Resident;

/**
 * Created by pascal on 2017-01-10.
 */
/*
@Controller
public class ResidentController {
    @Autowired
    private Api api;

    @Autowired
    ConsentService consentService;

    @RequestMapping("/resident")
    @ResponseBody
    public Response<DataResultSet<Resident>> careDocumentation() {
        boolean consentPosted = consentService.postConsent();
        if (consentPosted) {
            final GetCareDocumentation request = api.getOperationBuilder(GetLookupResidentBuilder.class)
                    .as(consentService.getCareActor())
                    .withSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                    .build();
            return request.execute();
        } else {
            return null;
        }
    }
}
*/