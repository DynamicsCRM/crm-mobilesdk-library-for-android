package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/26/2015.
 */
public final class AddPrincipalToQueueRequest extends OrganizationRequest {

    public AddPrincipalToQueueRequest() {
        this.setRequestName("AddPrincipalToQueue");
        this.setResponseType(new AddPrincipalToQueueResponse());
    }

    @Nullable
    public Entity getPrincipal() {
        if (this.getParameters().containsKey("Principal")) {
            return (Entity) this.getParameters().get("Principal");
        }

        return null;
    }

    public void setPrincipal(Entity value) {
        this.set("Principal", value);
    }

    public UUID getQueueId() {
        if (this.getParameters().containsKey("QueueId")) {
            return (UUID) this.getParameters().get("QueueId");
        }

        return new UUID(0L, 0L);
    }

    public void setQueueId(UUID value) {
        this.set("QueueId", value);
    }

    @Override
    public String getRequestBody() {
        this.set("Principal", getPrincipal());
        this.set("QueueId", getQueueId());
        return getSoapBody();
    }
}
