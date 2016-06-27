package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/31/2015.
 */
public final class RetrieveUserQueuesRequest extends OrganizationRequest {

    public RetrieveUserQueuesRequest() {
        this.setRequestName("RetrieveUserQueues");
        this.setResponseType(new RetrieveUserQueuesResponse());
    }

    public boolean getIncludePublic() {
        if (this.getParameters().containsKey("IncludePublic")) {
            return (boolean)this.getParameters().get("IncludePublic");
        }

        return false;
    }

    public void setIncludePublic(boolean value) {
        this.set("IncludePublic", value);
    }

    public UUID getUserId() {
        if (this.getParameters().containsKey("UserId")) {
            return (UUID) this.getParameters().get("UserId");
        }

        return new UUID(0L, 0L);
    }

    public void setUserId(UUID value) {
        this.set("UserId", value);
    }
}
