package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OptionSetValue;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/27/2015.
 */
public final class LoseOpportunityRequest extends OrganizationRequest {

    public LoseOpportunityRequest() {
        this.setResponseType(new LoseOpportunityResponse());
        this.setRequestName("LoseOpportunity");
    }

    @Nullable
    public Entity getOpportunityClose() {
        if (this.getParameters().containsKey("OpportunityClose")) {
            return (Entity) this.getParameters().get("OpportunityClose");
        }

        return null;
    }

    public void setOpportunityClose(Entity value) {
        this.set("OpportunityClose", value);
    }

    @Nullable
    public OptionSetValue getStatus() {
        if (this.getParameters().containsKey("Status")) {
            return (OptionSetValue) this.getParameters().get("Status");
        }

        return null;
    }

    public void setStatus(OptionSetValue value) {
        this.set("Status", value);
    }

    @Override
    public String getRequestBody() {
        this.set("OpportunityClose", getOpportunityClose());
        this.set("Status", getStatus());
        return getSoapBody();
    }
}
