package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.EntityReference;
import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/27/2015.
 */
public final class RouteToRequest extends OrganizationRequest {

    public RouteToRequest() {
        this.setRequestName("RouteTo");
        this.setResponseType(new RouteToResponse());
    }

    public UUID getQueueItemId() {
        if (this.getParameters().containsKey("QueueItemId")) {
            return (UUID) this.getParameters().get("QueueItemId");
        }

        return new UUID(0L, 0L);
    }

    public void setQueueItemId(UUID value) {
        this.set("QueueItemId", value);
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
        this.set("QueueItemId", getQueueItemId());
        this.set("Target", getTarget());
        return getSoapBody();
    }

}
