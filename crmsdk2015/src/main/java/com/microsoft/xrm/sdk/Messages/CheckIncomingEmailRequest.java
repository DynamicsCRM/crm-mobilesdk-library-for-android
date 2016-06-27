package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/26/2015.
 */
public final class CheckIncomingEmailRequest extends OrganizationRequest {

    public CheckIncomingEmailRequest() {
        this.setResponseType(new CheckIncomingEmailResponse());
        this.setRequestName("CheckIncomingEmail");
    }

    @Nullable
    public String getBcc() {
        if (this.getParameters().containsKey("Bcc")) {
            return (String) this.getParameters().get("Bcc");
        }

        return null;
    }

    public void setBcc(String value) {
        this.set("Bcc", value);
    }

    @Nullable
    public String getCc() {
        if (this.getParameters().containsKey("Cc")) {
            return (String) this.getParameters().get("Cc");
        }

        return null;
    }

    public void setCc(String value) {
        this.set("Cc", value);
    }

    @Nullable
    public Entity getExtraProperties() {
        if (this.getParameters().containsKey("ExtraProperties")) {
            return (Entity) this.getParameters().get("ExtraProperties");
        }

        return null;
    }

    public void setExtraProperties(Entity value) {
        this.set("ExtraProperties", value);
    }

    @Nullable
    public String getFrom() {
        if (this.getParameters().containsKey("From")) {
            return (String) this.getParameters().get("From");
        }

        return null;
    }

    public void setFrom(String value) {
        this.set("From", value);
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

    @Nullable
    public String getTo() {
        if (this.getParameters().containsKey("To")) {
            return (String) this.getParameters().get("To");
        }

        return null;
    }

    public void setTo(String value) {
        this.set("To", value);
    }

    @Override
    public String getRequestBody() {
        this.set("Bcc", getBcc());
        this.set("Cc", getCc());
        this.set("ExtraProperties", getExtraProperties());
        this.set("From", getFrom());
        this.set("MessageId", getMessageId());
        this.set("Subject", getSubject());
        this.set("To", getTo());
        return getSoapBody();
    }
}
