package com.threadsafestudio.hip;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.RegisterPdlConsentBuilder;
import se.hip.sdk.api.core.*;
import se.hip.sdk.api.operation.RegisterPdlConsent;

/**
 * Created by pascal on 2017-01-11.
 */
public class ConsentService {
    public interface RequestExecutor {
        Response execute();
    }

    @Autowired
    private Api api;

    public CareActorPrincipal getCareActor() {
        return PrincipalFactory.createCareActorPrincipal(
                "sessionId",
                "Vård och behandling",
                "assignmentId",
                "careActorId",
                "careUnitId",
                "careProviderId",
                "careActorHsaTitle",
                "careActorPrescriptionCode",
                "Läsa;alla;SJF"
        );
    }

    public boolean postConsent() {
        final SubjectOfCare soc = DefaultSubjectOfCare.create("191212121212");

        // Register a consent for
        // Tolvan (191212121212)
        final RegisterPdlConsent postConsent = api.getOperationBuilder(RegisterPdlConsentBuilder.class)
                .as(getCareActor())
                .forSubjectOfCare(soc)
                .isEmergency(false)
                .isForRequestingCareActorOnly(false)
                .validFrom(DateTime.now().minusYears(1).toDate())
                .expiresAt(DateTime.now().toDate())
                .build();
        final Response resp = postConsent.execute();
        return resp.getStatus().getErrors().isEmpty();
    }
}
