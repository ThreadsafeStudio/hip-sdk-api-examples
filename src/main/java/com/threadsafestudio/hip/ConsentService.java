package com.threadsafestudio.hip;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import se.hip.sdk.api.Api;
import se.hip.sdk.api.RegisterDruglistConsentBuilder;
import se.hip.sdk.api.RegisterPdlConsentBuilder;
import se.hip.sdk.api.core.*;
import se.hip.sdk.api.operation.RegisterDruglistConsent;
import se.hip.sdk.api.operation.RegisterPdlConsent;
import se.hip.sdk.service.dto.ExtendedUnitInformation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by pascal on 2017-01-11.
 */
public class ConsentService {
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

    public DruglistCareActorPrincipal getDrugListCareActor() {
        return new DemoDrugListCareActor();
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

    public boolean postDruglistConsent() {
        final RegisterDruglistConsent request = api.getOperationBuilder(RegisterDruglistConsentBuilder.class)
                .as(DruglistCareActorPrincipalImpl.create(getCareActor(), new ExtendedUnitInformation()))
                .forSubjectOfCare(DefaultSubjectOfCare.create("191212121212"))
                .isOngoing(true)
                .build();

        final Response<Serializable> resp = request.execute();
        return resp.getStatus().getErrors().isEmpty();
    }
}
