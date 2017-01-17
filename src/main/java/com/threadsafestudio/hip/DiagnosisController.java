package com.threadsafestudio.hip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.GetCareContactsBuilder;
import se.hip.sdk.api.GetDiagnosisBuilder;
import se.hip.sdk.api.carecontacts.CareContact;
import se.hip.sdk.api.core.DefaultSubjectOfCare;
import se.hip.sdk.api.core.Response;
import se.hip.sdk.api.diagnosis.Diagnosis;
import se.hip.sdk.api.operation.GetCareContacts;
import se.hip.sdk.api.operation.GetDiagnosis;
import se.hip.sdk.api.query.result.DataResultSet;

/**
 * Created by pascal on 2017-01-10.
 */
@Controller
public class DiagnosisController {
    @Autowired
    private Api api;

    @Autowired
    ConsentService consentService;

    @RequestMapping("/diagnosis")
    @ResponseBody
    public Response<DataResultSet<Diagnosis>> careDocumentation() {
        boolean consentPosted = consentService.postConsent();
        if (consentPosted) {
            final GetDiagnosis request = api.getOperationBuilder(GetDiagnosisBuilder.class)
                    .as(consentService.getCareActor())
                    .withSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                    .build();
            return request.execute();
        } else {
            return null;
        }
    }
}
