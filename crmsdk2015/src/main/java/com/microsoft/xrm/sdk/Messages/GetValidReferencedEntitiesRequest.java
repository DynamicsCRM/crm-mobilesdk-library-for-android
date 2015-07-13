package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/24/2015.
 */
public final class GetValidReferencedEntitiesRequest extends OrganizationRequest {

    public GetValidReferencedEntitiesRequest()
    {
        this.setRequestName("GetValidReferencedEntities");
    }

    @Nullable
    public String getReferencingEntityName() {
        if (this.getParameters().containsKey("ReferencingEntityName")) {
            return this.getParameters().get("ReferencingEntityName").toString();
        }

        return null;
    }

    public void setReferencingEntityName(String value) {
        this.set("ReferencingEntityName", value);
    }


}
