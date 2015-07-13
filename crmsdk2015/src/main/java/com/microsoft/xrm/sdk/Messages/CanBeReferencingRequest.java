package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/24/2015.
 */
public final class CanBeReferencingRequest extends OrganizationRequest {

    public CanBeReferencingRequest() {
        this.setRequestName("CanBeReferencing");
        this.setEntityName(null);
    }

    @Nullable
    public String getEntityName() {
        if (this.getParameters().containsKey("EntityName")) {
            return this.getParameters().get("EntityName").toString();
        }
        return null;
    }

    public void setEntityName(String value) {
        this.set("EntityName", value);
    }
}
