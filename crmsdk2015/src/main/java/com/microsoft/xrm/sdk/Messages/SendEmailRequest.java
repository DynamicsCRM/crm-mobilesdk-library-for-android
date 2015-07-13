package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/27/2015.
 */
public final class SendEmailRequest extends OrganizationRequest {

    public SendEmailRequest() {
        this.setRequestName("SendEmail");
        this.setResponseType(new SendEmailResponse());
    }

    public UUID getEmailId() {
        if (this.getParameters().containsKey("EmailId")) {
            return (UUID) this.getParameters().get("EmailId");
        }

        return new UUID(0L, 0L);
    }

    public void setEmailId(UUID value) {
        this.set("EmailId", value);
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

    @Nullable
    public String getTrackingToken() {
        if (this.getParameters().containsKey("TrackingToken")) {
            return (String) this.getParameters().get("TrackingToken");
        }

        return null;
    }

    public void setTrackingToken(String value) {
        this.set("TrackingToken", value);
    }

    @Override
    public String getRequestBody() {
        this.set("EmailId", getEmailId());
        this.set("IssueSend", getIssueSend());
        this.set("TrackingToken", getTrackingToken());
        return getSoapBody();
    }

}
