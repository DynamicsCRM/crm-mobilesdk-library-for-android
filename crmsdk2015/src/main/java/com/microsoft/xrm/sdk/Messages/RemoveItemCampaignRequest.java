package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/31/2015.
 */
public final class RemoveItemCampaignRequest extends OrganizationRequest {

    public RemoveItemCampaignRequest() {
        this.setResponseType(new RemoveItemCampaignResponse());
        this.setRequestName("RemoveItemCampaign");
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

    public UUID getCampaignId() {
        if (this.getParameters().containsKey("CampaignId")) {
            return (UUID) this.getParameters().get("CampaignId");
        }

        return new UUID(0L, 0L);
    }

    public void setCampaignId(UUID value) {
        this.set("CampaignId", value);
    }

    @Override
    public String getRequestBody() {
        this.set("CampaignId", getCampaignId());
        this.set("EntityId", getEntityId());
        return getSoapBody();
    }
}
