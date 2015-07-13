package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OptionSetValue;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/27/2015.
 */
public final class CloseIncidentRequest extends OrganizationRequest {

    public CloseIncidentRequest() {
        this.setRequestName("CloseIncident");
        this.setResponseType(new CloseIncidentResponse());
    }

    @Nullable
    public Entity getIncidentResolution() {
        if (this.getParameters().containsKey("IncidentResolution")) {
            return (Entity) this.getParameters().get("IncidentResolution");
        }

        return null;
    }

    public void setIncidentResolution(Entity value) {
        this.set("IncidentResolution", value);
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
        this.set("IncidentResolution", getIncidentResolution());
        this.set("Status", getStatus());
        return getSoapBody();
    }
}
