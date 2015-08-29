package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/27/2015.
 */
public final class RemoveMemberListRequest extends OrganizationRequest {

    public RemoveMemberListRequest() {
        this.setRequestName("RemoveMemberList");
        this.setResponseType(new RemoveMemberListResponse());
    }

    public UUID getEntityId() {
        if (this.getParameters().containsKey("EntityId")) {
            return (UUID) this.getParameters().get("EntityId");
        }

        return new UUID(0L, 0L);
    }

    public void setEntityId(UUID value) {
        this.set("EntityId", value);
    }

    public UUID getListId() {
        if (this.getParameters().containsKey("ListId")) {
            return (UUID) this.getParameters().get("ListId");
        }

        return new UUID(0L, 0L);
    }

    public void setListId(UUID value) {
        this.set("ListId", value);
    }

    @Override
    public String getRequestBody() {
        this.set("EntityId", getEntityId());
        this.set("ListId", getListId());
        return getSoapBody();
    }

}
