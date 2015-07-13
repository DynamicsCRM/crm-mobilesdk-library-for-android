package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/24/2015.
 */
public final class GetValidReferencingEntitiesRequest extends OrganizationRequest {

    public GetValidReferencingEntitiesRequest() {
        this.setRequestName("GetValidReferencingEntities");
    }

    @Nullable
    public String getReferencedEntityName() {
        if (this.getParameters().containsKey("ReferencedEntityName")) {
            return this.getParameters().get("ReferencedEntityName").toString();
        }
        return null;
    }

    public void setReferencedEntityName(String value) {
        this.set("ReferencedEntityName", value);
    }
}
