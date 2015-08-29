package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/27/2015.
 */
public final class CopyDynamicListToStaticRequest extends OrganizationRequest {

    public CopyDynamicListToStaticRequest() {
        this.setResponseType(new CopyDynamicListToStaticResponse());
        this.setRequestName("CopyDynamicListToStatic");
    }

    public UUID getListId() {
        if (this.getParameters().containsKey("ListId")) {
            return (UUID) this.getParameters().get("ListId");
        }

        return null;
    }
    public void setListId(UUID value) {
        this.set("ListId", value);
    }

    @Override
    public String getRequestBody() {
        this.set("ListId", getListId());
        return getSoapBody();
    }
}
