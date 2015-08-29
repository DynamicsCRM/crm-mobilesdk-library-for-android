package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/27/2015.
 */
public final class ReleaseToQueueRequest extends OrganizationRequest {

    public ReleaseToQueueRequest() {
        this.setRequestName("ReleaseToQueue");
        this.setResponseType(new ReleaseToQueueResponse());
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

    @Override
    public String getRequestBody() {
        this.set("QueueItemId", getQueueItemId());
        return getSoapBody();
    }

}
