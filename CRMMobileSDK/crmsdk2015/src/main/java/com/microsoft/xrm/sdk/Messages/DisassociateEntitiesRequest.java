package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.EntityReference;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/27/2015.
 */
public final class DisassociateEntitiesRequest extends OrganizationRequest {

    public DisassociateEntitiesRequest() {
        this.setRequestName("DisassociateEntities");
        this.setResponseType(new DisassociateEntitiesResponse());
    }

    @Nullable
    public EntityReference getMoniker1() {
        if (this.getParameters().containsKey("Moniker1")) {
            return (EntityReference) this.getParameters().get("Moniker1");
        }

        return null;
    }

    public void setMoniker1(EntityReference value) {
        this.set("Moniker1", value);
    }

    @Nullable
    public EntityReference getMoniker2() {
        if (this.getParameters().containsKey("Moniker2")) {
            return (EntityReference) this.getParameters().get("Moniker2");
        }

        return null;
    }

    public void setMoniker2(EntityReference value) {
        this.set("Moniker2", value);
    }

    @Nullable
    public String getRelationshipName() {
        if (this.getParameters().containsKey("RelationshipName")) {
            return (String) this.getParameters().get("RelationshipName");
        }

        return null;
    }

    public void setRelationshipName(String value) {
        this.set("RelationshipName", value);
    }

    @Override
    public String getRequestBody() {
        this.set("Moniker1", getMoniker1());
        this.set("Moniker2", getMoniker2());
        this.set("RelationshipName", getRelationshipName());
        return getSoapBody();
    }

}
