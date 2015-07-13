package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.EntityReference;
import com.microsoft.xrm.sdk.OrganizationRequest;
import com.microsoft.xrm.sdk.OrganizationResponse;

/**
 * Created on 3/24/2015.
 */
public final class DeleteRequest extends OrganizationRequest {

    public DeleteRequest() {
        this.setRequestName("Delete");
        this.setResponseType(new OrganizationResponse());
        this.setTarget(null);
    }

    @Nullable
    public EntityReference getTarget() {
        if (this.getParameters().containsKey("Target")) {
            return (EntityReference) this.getParameters().get("Target");
        }
        return null;
    }

    public void setTarget(EntityReference value) {
        this.set("Target", value);
    }

    @Override
    public String getRequestBody() {
        this.set("Target", getTarget());
        return getSoapBody();
    }
}
