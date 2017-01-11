package com.threadsafestudio.hip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.GetCareDocumentationBuilder;
import se.hip.sdk.api.GetCarePlansBuilder;
import se.hip.sdk.api.GetVaccinationHistory1Builder;
import se.hip.sdk.api.careplans.CarePlan;
import se.hip.sdk.api.core.DefaultSubjectOfCare;
import se.hip.sdk.api.core.Response;
import se.hip.sdk.api.operation.GetCarePlans;
import se.hip.sdk.api.operation.GetVaccinationHistory1;
import se.hip.sdk.api.query.result.DataResultSet;
import se.hip.sdk.api.vaccinations.Vaccination;

/**
 * Created by pascal on 2017-01-10.
 */
@Controller
public class CarePlansController {
    @Autowired
    private Api api;

    @Autowired
    ConsentService consentService;

    @RequestMapping("/careplans")
    @ResponseBody
    public Response<DataResultSet<CarePlan>> careDocumentation() {
        boolean consentPosted = consentService.postConsent();
        if (consentPosted) {
            final GetCarePlans request =
                    api.getOperationBuilder(GetCarePlansBuilder.class)
                        .as(consentService.getCareActor())
                        .withSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                        .build();
            return request.execute();
        } else {
            return null;
        }
    }
}
