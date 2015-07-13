package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/26/2015.
 */
public final class CheckPromoteEmailRequest extends OrganizationRequest {

    public CheckPromoteEmailRequest() {
        this.setResponseType(new CheckPromoteEmailResponse());
        this.setRequestName("CheckPromoteEmail");
    }

    public int getDirectionCode() {
        if (this.getParameters().containsKey("DirectionCode")) {
            return (int) this.getParameters().get("DirectionCode");
        }
        return 0;
    }

    public void setDirectionCode(int value) {
        this.set("DirectionCode", value);
    }

    @Nullable
    public String getMessageId() {
        if (this.getParameters().containsKey("MessageId")) {
            return (String) this.getParameters().get("MessageId");
        }

        return null;
    }

    public void setMessageId(String value) {
        this.set("MessageId", value);
    }

    @Nullable
    public String getSubject() {
        if (this.getParameters().containsKey("Subject")) {
            return (String) this.getParameters().get("Subject");
        }

        return null;
    }

    public void setSubject(String value) {
        this.set("Subject", value);
    }

    @Override
    public String getRequestBody() {
        this.set("DirectionCode", getDirectionCode());
        this.set("MessageId", getMessageId());
        this.set("Subject", getSubject());
        return getSoapBody();
    }
}
