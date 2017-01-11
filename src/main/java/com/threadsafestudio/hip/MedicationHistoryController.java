package com.threadsafestudio.hip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.GetCareListingBuilder;
import se.hip.sdk.api.GetMedicationHistoryBuilder;
import se.hip.sdk.api.carelisting.Listing;
import se.hip.sdk.api.core.DefaultSubjectOfCare;
import se.hip.sdk.api.core.Response;
import se.hip.sdk.api.medicationhistory.MedicationHistory;
import se.hip.sdk.api.operation.GetCareListing;
import se.hip.sdk.api.operation.GetMedicationHistory;
import se.hip.sdk.api.query.result.DataResultSet;

/**
 * Created by pascal on 2017-01-10.
 */
@Controller
public class MedicationHistoryController {
    @Autowired
    private Api api;

    @Autowired
    ConsentService consentService;

    @RequestMapping("/medicationhistory")
    @ResponseBody
    public Response<DataResultSet<MedicationHistory>> careDocumentation() {
        boolean consentPosted = consentService.postConsent();
        if (consentPosted) {
            final GetMedicationHistory request =
                    api.getOperationBuilder(GetMedicationHistoryBuilder.class)
                        .as(consentService.getCareActor())
                        .withSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                        .build();
            return request.execute();
        } else {
            return null;
        }
    }
}
