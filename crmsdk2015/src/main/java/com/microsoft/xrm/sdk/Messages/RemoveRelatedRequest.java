package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.EntityReference;
import com.microsoft.xrm.sdk.OrganizationRequest;

public final class RemoveRelatedRequest extends OrganizationRequest {

    public RemoveRelatedRequest() {
        this.setResponseType(new RemoveRelatedResponse());
        this.setRequestName("RemoveRelated");
    }

    @Nullable
    public EntityReference[] getTarget() {
        if (this.getParameters().containsKey("Target")) {
            return (EntityReference[]) this.getParameters().get("Target");
        }

        return null;
    }

    public void setTarget(EntityReference[] value) {
        this.set("Target", value);
    }

    @Override
    public String getRequestBody() {
        this.set("Target", getTarget());
        return getSoapBody();
    }
}
