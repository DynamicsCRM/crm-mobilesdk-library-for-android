package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.EntityReference;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/26/2015.
 */
public final class AssignRequest extends OrganizationRequest {

    public AssignRequest() {
        this.setRequestName("Assign");
        this.setResponseType(new AssignResponse());
    }

    @Nullable
    public EntityReference getAssignee() {
        if (this.getParameters().containsKey("Assignee")) {
            return (EntityReference) this.getParameters().get("Assignee");
        }

        return null;
    }

    public void setAssignee(EntityReference value) {
        this.set("Assignee", value);
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
        this.set("Assignee", getAssignee());
        this.set("Target", getTarget());
        return getSoapBody();
    }
}
