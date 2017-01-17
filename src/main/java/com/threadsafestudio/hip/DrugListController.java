package com.threadsafestudio.hip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.GetDruglistBuilder;
import se.hip.sdk.api.GetMedicationHistoryBuilder;
import se.hip.sdk.api.core.DefaultSubjectOfCare;
import se.hip.sdk.api.core.Response;
import se.hip.sdk.api.druglist.Drug;
import se.hip.sdk.api.medicationhistory.MedicationHistory;
import se.hip.sdk.api.operation.GetDruglist;
import se.hip.sdk.api.operation.GetMedicationHistory;
import se.hip.sdk.api.query.result.DataResultSet;

/**
 * Created by pascal on 2017-01-10.
 */
@Controller
public class DrugListController {
    @Autowired
    private Api api;

    @Autowired
    ConsentService consentService;

    @RequestMapping("/druglist")
    @ResponseBody
    public Response<DataResultSet<Drug>> careDocumentation() {
        boolean consentPosted = consentService.postConsent();
        boolean drugListConsentPosted = consentService.postDruglistConsent();
        if (consentPosted && drugListConsentPosted) {
            final GetDruglist request =
                    api.getOperationBuilder(GetDruglistBuilder.class)
                        .as(consentService.getDrugListCareActor())
                        .withSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                        .build();
            return request.execute();
        } else {
            return null;
        }
    }
}
