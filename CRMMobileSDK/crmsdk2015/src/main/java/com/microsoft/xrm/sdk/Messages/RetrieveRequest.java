package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.EntityReference;
import com.microsoft.xrm.sdk.OrganizationRequest;
import com.microsoft.xrm.sdk.ColumnSet;

/**
 * Created on 3/24/2015.
 */
public final class RetrieveRequest extends OrganizationRequest {

    public RetrieveRequest() {
        this.setRequestName("Retrieve");
        this.setResponseType(new RetrieveResponse());
        this.setTarget(null);
        this.setColumnSet(null);
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
    public ColumnSet getColumnSet() {
        if (this.getParameters().containsKey("ColumnSet")) {
            return (ColumnSet) this.getParameters().get("ColumnSet");
        }
        return null;
    }

    public void setColumnSet(ColumnSet value) {
        this.set("ColumnSet", value);
    }

    @Override
    public String getRequestBody() {
        this.set("Target", getTarget());
        this.set("ColumnSet", getColumnSet());
        return getSoapBody();
    }

//    @Nullable
//    public RelationshipQueryCollection getRelatedEntitiesQuery() {
//        if (this.getParameters().containsKey("RelatedEntitiesQuery")) {
//            return (RelationshipQueryCollection) this.getParameters().get("RelatedEntitiesQuery");
//        }
//        return null;
//    }
//
//    public void setRelatedEntitiesQuery(RelationshipQueryCollection value) {
//        this.set("RelatedEntitiesQuery", value);
//    }
}
