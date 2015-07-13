package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/26/2015.
 */
public final class AddItemCampaignRequest extends OrganizationRequest {

    public AddItemCampaignRequest() {
        this.setResponseType(new AddItemCampaignResponse());
        this.setRequestName("AddItemCampaign");
    }

    @Override
    public String getRequestBody() {
        this.set("CampaignId", getCampaignId());
        this.set("EntityId", getEntityId());
        this.set("EntityName", getEntityName());
        return getSoapBody();
    }

    public UUID getCampaignId() {
        if (this.getParameters().containsKey("CampaignId")) {
            return (UUID) this.getParameters().get("CampaignId");
        }

        return new UUID(0L, 0L);
    }

    public UUID getEntityId() {
        if (this.getParameters().containsKey("EntityId")) {
            return (UUID) this.getParameters().get("EntityId");
        }

        return new UUID(0L,0L);
    }

    @Nullable
    public String getEntityName() {
        if (this.getParameters().containsKey("EntityName")) {
            return this.getParameters().get("EntityName").toString();
        }

        return null;
    }

    public void setCampaignId(UUID campaignId) {
        this.set("CampaignId", campaignId);
    }

    public void setEntityId(UUID entityId) {
        this.set("EntityId", entityId);
    }

    public void setEntityName(String entityName) {
        this.set("EntityName", entityName);
    }
}
