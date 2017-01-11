package com.threadsafestudio.hip;

import se.hip.sdk.api.core.DruglistCareActorPrincipal;
import se.hip.sdk.api.core.PdlPermission;

import java.util.List;
import java.util.Map;

/**
 * Created by pascal on 2017-01-11.
 */
public class DemoDrugListCareActor implements DruglistCareActorPrincipal {
    @Override
    public String getCareUnitPostalAddress() {
        return "CareUnitPostalAddress";
    }

    @Override
    public String getCareUnitPostalNumber() {
        return "CareUnitPostalNumber";
    }

    @Override
    public String getCareUnitTelephone() {
        return "CareUnitTelephone";
    }

    @Override
    public String getCareUnitPrescriptionCode() {
        return "CareUnitPrescriptionCode";
    }

    @Override
    public String getCareUnitOrgNumber() {
        return "CareUnitOrgNumber";
    }

    @Override
    public String getCareActorId() {
        return "CareActorId";
    }

    @Override
    public String getCareActorAssignmentId() {
        return "CareActorAssignmentId";
    }

    @Override
    public String getCareActorPrescriptionCode() {
        return "CareActorPrescriptionCode";
    }

    @Override
    public String getCareActorTitle() {
        return "CareActorHsaTitle";
    }

    @Override
    public String getHsaTitleCode() {
        return "HsaTitleCode";
    }

    @Override
    public String getFirstName() {
        return "FirstName";
    }

    @Override
    public String getMiddleAndSurname() {
        return "MiddleAndSurname";
    }

    @Override
    public String getCareProviderHsaId() {
        return "CareProviderHsaId";
    }

    @Override
    public String getCareUnitHsaId() {
        return "CareUnitHsaId";
    }

    @Override
    public String getCareUnitName() {
        return "CareUnitName";
    }

    @Override
    public Map<String, List<PdlPermission>> getCareActorPermissions() {
        return null;
    }

    @Override
    public String getCommissionPurpose() {
        return "CommissionPurpose";
    }

    @Override
    public String getSessionId() {
        return "SessionId";
    }
}
