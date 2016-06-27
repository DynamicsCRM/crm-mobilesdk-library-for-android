package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.EntityReference;
import com.microsoft.xrm.sdk.OptionSetValue;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/27/2015.
 */
public final class QualifyLeadRequest extends OrganizationRequest {

    public QualifyLeadRequest() {
        this.setRequestName("QualifyLead");
        this.setResponseType(new QualifyLeadResponse());
    }

    public static QualifyLeadRequest build() {
        return new QualifyLeadRequest();
    }

    public boolean getCreateAccount() {
        if (this.getParameters().containsKey("CreateAccount")) {
            return (boolean) this.getParameters().get("CreateAccount");
        }

        return false;
    }

    public QualifyLeadRequest setCreateAccount(boolean value) {
        this.set("CreateAccount", value);
        return this;
    }

    public boolean getCreateContact() {
        if (this.getParameters().containsKey("CreateContact")) {
            return (boolean) this.getParameters().get("CreateContact");
        }

        return false;
    }

    public QualifyLeadRequest setCreateContact(boolean value) {
        this.set("CreateContact", value);
        return this;
    }

    public boolean getCreateOpportunity() {
        if (this.getParameters().containsKey("CreateOpportunity")) {
            return (boolean) this.getParameters().get("CreateOpportunity");
        }

        return false;
    }

    public QualifyLeadRequest setCreateOpportunity(boolean value) {
        this.set("CreateOpportunity", value);
        return this;
    }

    @Nullable
    public EntityReference getLeadId() {
        if (this.getParameters().containsKey("LeadId")) {
            return (EntityReference) this.getParameters().get("LeadId");
        }

        return null;
    }

    public QualifyLeadRequest setLeadId(EntityReference value) {
        this.set("LeadId", value);
        return this;
    }

    @Nullable
    public EntityReference getOpportunityCurrencyId() {
        if (this.getParameters().containsKey("OpportunityCurrencyId")) {
            return (EntityReference) this.getParameters().get("OpportunityCurrencyId");
        }

        return null;
    }

    public QualifyLeadRequest setOpportunityCurrencyId(EntityReference value) {
        this.set("OpportunityCurrencyId", value);
        return this;
    }

    @Nullable
    public EntityReference getOpportunityCustomerId() {
        if (this.getParameters().containsKey("OpportunityCustomerId")) {
            return (EntityReference) this.getParameters().get("OpportunityCustomerId");
        }

        return null;
    }

    public QualifyLeadRequest setOpportunityCustomerId(EntityReference value) {
        this.set("OpportunityCustomerId", value);
        return this;
    }

    @Nullable
    public EntityReference getSourceCampaignId() {
        if (this.getParameters().containsKey("SourceCampaignId")) {
            return (EntityReference) this.getParameters().get("SourceCampaignId");
        }

        return null;
    }

    public QualifyLeadRequest setSourceCampaignId(EntityReference value) {
        this.set("SourceCampaignId", value);
        return this;
    }

    @Nullable
    public OptionSetValue getStatus() {
        if (this.getParameters().containsKey("Status")) {
            return (OptionSetValue) this.getParameters().get("Status");
        }

        return null;
    }

    public QualifyLeadRequest setStatus(OptionSetValue value) {
        this.set("Status", value);
        return this;
    }

    @Override
    public String getRequestBody() {
        this.set("CreateAccount", getCreateAccount());
        this.set("CreateContact", getCreateContact());
        this.set("CreateOpportunity", getCreateOpportunity());
        this.set("LeadId", getLeadId());
        this.set("OpportunityCurrencyId", getOpportunityCurrencyId());
        this.set("OpportunityCustomerId", getOpportunityCustomerId());
        this.set("SourceCampaignId", getSourceCampaignId());
        this.set("Status", getStatus());
        return getSoapBody();
    }

}
