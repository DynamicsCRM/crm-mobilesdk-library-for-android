package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/27/2015.
 */
public final class PickFromQueueRequest extends OrganizationRequest {

    public PickFromQueueRequest() {
        this.setResponseType(new PickFromQueueResponse());
        this.setRequestName("PickFromQueue");
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

    public boolean getRemoveQueueItem() {
        if (this.getParameters().containsKey("RemoveQueueItem")) {
            return (boolean) this.getParameters().get("RemoveQueueItem");
        }

        return false;
    }

    public void setRemoveQueueItem(boolean value) {
        this.set("RemoveQueueItem", value);
    }

    public UUID getWorkerId() {
        if (this.getParameters().containsKey("WorkerId")) {
            return (UUID) this.getParameters().get("WorkerId");
        }

        return new UUID(0L, 0L);
    }

    public void setWorkerId(UUID value) {
        this.set("WorkerId", value);
    }

    @Override
    public String getRequestBody() {
        this.set("QueueItemId", getQueueItemId());
        this.set("RemoveQueueItem", getRemoveQueueItem());
        this.set("WorkerId", getWorkerId());
        return getSoapBody();
    }

}
