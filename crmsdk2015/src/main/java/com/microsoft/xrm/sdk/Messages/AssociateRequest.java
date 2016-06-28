package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.EntityReference;
import com.microsoft.xrm.sdk.EntityReferenceCollection;
import com.microsoft.xrm.sdk.OrganizationRequest;
import com.microsoft.xrm.sdk.Relationship;

public final class AssociateRequest extends OrganizationRequest {

    public AssociateRequest() {
        this.setRequestName("Associate");
        this.setResponseType(new AssociateResponse());
        this.setTarget(null);
        this.setRelationship(null);
        this.setRelatedEntities(null);
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

    @Nullable
    public Relationship getRelationship() {
        if (this.getParameters().containsKey("Relationship")) {
            return (Relationship) this.getParameters().get("Relationship");
        }
        return null;
    }

    public void setRelationship(Relationship value) {
        this.set("Relationship", value);
    }

    @Nullable
    public EntityReferenceCollection getRelatedEntities() {
        if (this.getParameters().containsKey("RelatedEntities")) {
            return (EntityReferenceCollection)this.getParameters().get("RelatedEntities");
        }
        return null;
    }

    public void setRelatedEntities(EntityReferenceCollection value) {
        this.set("RelatedEntities", value);
    }

    @Override
    public String getRequestBody() {
        this.set("Target", getTarget());
        this.set("Relationship", getRelationship());
        this.set("RelatedEntities", getRelatedEntities());
        return getSoapBody();
    }
}
