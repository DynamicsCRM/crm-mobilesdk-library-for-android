package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.EntityReference;
import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/27/2015.
 */
public final class MergeRequest extends OrganizationRequest {

    public MergeRequest() {
        this.setRequestName("Merge");
        this.setResponseType(new MergeResponse());
    }

    public boolean getPerformParentingChecks() {
        if (this.getParameters().containsKey("PerformParentingChecks")) {
            return (boolean) this.getParameters().get("PerformParentingChecks");
        }

        return false;
    }

    public void setPerformParentingChecks(boolean value) {
        this.set("PerformParentingChecks", value);
    }

    public UUID getSubordinateId() {
        if (this.getParameters().containsKey("SubordinateId")) {
            return (UUID) this.getParameters().get("SubordinateId");
        }

        return new UUID(0L, 0L);
    }

    public void setSubordinateId(UUID value) {
        this.set("SubordinateId", value);
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
    public Entity getUpdateContent() {
        if (this.getParameters().containsKey("UpdateContent")) {
            return (Entity) this.getParameters().get("UpdateContent");
        }

        return null;
    }

    public void setUpdateContent(Entity value) {
        this.set("UpdateContent", value);
    }

    @Override
    public String getRequestBody() {
        this.set("PerformParentingChecks", getPerformParentingChecks());
        this.set("SubordinateId", getSubordinateId());
        this.set("Target", getTarget());
        this.set("UpdateContent", getUpdateContent());
        return getSoapBody();
    }

}
