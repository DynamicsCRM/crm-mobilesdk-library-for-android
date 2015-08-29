package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.EntityReference;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/27/2015.
 */
public final class SetRelatedRequest extends OrganizationRequest {

    public SetRelatedRequest() {
        this.setResponseType(new SetRelatedResponse());
        this.setRequestName("SetRelated");
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
