package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/27/2015.
 */
public final class SendFaxRequest extends OrganizationRequest {

    public SendFaxRequest() {
        this.setResponseType(new SendFaxResponse());
        this.setRequestName("SendFax");
    }

    public UUID getFaxId() {
        if (this.getParameters().containsKey("FaxId")) {
            return (UUID) this.getParameters().get("FaxId");
        }

        return new UUID(0L, 0L);
    }

    public void setFaxId(UUID value) {
        this.set("FaxId", value);
    }

    public boolean getIssueSend() {
        if (this.getParameters().containsKey("IssueSend")) {
            return (boolean) this.getParameters().get("IssueSend");
        }

        return false;
    }

    public void setIssueSend(boolean value) {
        this.set("IssueSend", value);
    }

    @Override
    public String getRequestBody() {
        this.set("FaxId", getFaxId());
        this.set("IssueSend", getIssueSend());
        return getSoapBody();
    }

}
