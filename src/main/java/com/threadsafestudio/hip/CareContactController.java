package com.threadsafestudio.hip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.GetCareContactsBuilder;
import se.hip.sdk.api.GetCareDocumentationBuilder;
import se.hip.sdk.api.carecontacts.CareContact;
import se.hip.sdk.api.caredocumentation.CareDocumentation;
import se.hip.sdk.api.core.DefaultSubjectOfCare;
import se.hip.sdk.api.core.Response;
import se.hip.sdk.api.operation.GetCareContacts;
import se.hip.sdk.api.operation.GetCareDocumentation;
import se.hip.sdk.api.query.result.DataResultSet;

/**
 * Created by pascal on 2017-01-10.
 */
@Controller
public class CareContactController {
    @Autowired
    private Api api;

    @Autowired
    ConsentService consentService;

    @RequestMapping("/carecontacts")
    @ResponseBody
    public Response<DataResultSet<CareContact>> careDocumentation() {
        boolean consentPosted = consentService.postConsent();
        if (consentPosted) {
            final GetCareContacts request = api.getOperationBuilder(GetCareContactsBuilder.class)
                    .as(consentService.getCareActor())
                    .withSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                    .build();
            return request.execute();
        } else {
            return null;
        }
    }
}
