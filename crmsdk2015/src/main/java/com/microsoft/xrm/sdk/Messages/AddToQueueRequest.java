package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.EntityReference;
import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/26/2015.
 */
public final class AddToQueueRequest extends OrganizationRequest {

    public AddToQueueRequest() {
        this.setRequestName("AddToQueue");
        this.setResponseType(new AddToQueueResponse());
    }

    public UUID getDestinationQueueId() {
        if (this.getParameters().containsKey("DestinationQueueId")) {
            return (UUID) this.getParameters().get("DestinationQueueId");
        }

        return new UUID(0L, 0L);
    }

    public void setDestinationQueueId(UUID value) {
        this.set("DestinationQueueId", value);
    }

    public UUID getSourceQueueId() {
        if (this.getParameters().containsKey("SourceQueueId")) {
            return (UUID) this.getParameters().get("SourceQueueId");
        }

        return new UUID(0L, 0L);
    }

    public void setSourceQueueId(UUID value) {
        this.set("SourceQueueId", value);
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
    public Entity getQueueItemProperties() {
        if (this.getParameters().containsKey("QueueItemProperties")) {
            return (Entity) this.getParameters().get("QueueItemProperties");
        }

        return null;
    }

    public void setQueueItemProperties(Entity value) {
        this.set("QueueItemProperties", value);
    }

    @Override
    public String getRequestBody() {
        this.set("DestinationQueueId", getDestinationQueueId());
        this.set("SourceQueueId", getSourceQueueId());
        this.set("Target", getTarget());
        this.set("QueueItemProperties", getQueueItemProperties());
        return getSoapBody();
    }
}
