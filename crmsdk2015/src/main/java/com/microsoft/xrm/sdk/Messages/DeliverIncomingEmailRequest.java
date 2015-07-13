package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.EntityCollection;
import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.Date;

/**
 * Created on 3/27/2015.
 */
public final class DeliverIncomingEmailRequest extends OrganizationRequest {

    public DeliverIncomingEmailRequest() {
        this.setResponseType(new DeliverIncomingEmailResponse());
        this.setRequestName("DeliverIncomingEmail");
    }

    @Nullable
    public EntityCollection getAttachments() {
        if (this.getParameters().containsKey("Attachments")) {
            return (EntityCollection) this.getParameters().get("Attachments");
        }

        return null;
    }

    public void setAttachments(EntityCollection value) {
        this.set("Attachments", value);
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
    public String getBody() {
        if (this.getParameters().containsKey("Body")) {
            return (String) this.getParameters().get("Body");
        }

        return null;
    }

    public void setBody(String value) {
        this.set("Body", value);
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
    public String getImportance() {
        if (this.getParameters().containsKey("Importance")) {
            return (String) this.getParameters().get("Importance");
        }

        return null;
    }

    public void setImportance(String value) {
        this.set("Importance", value);
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
    public Date getReceivedOn() {
        if (this.getParameters().containsKey("ReceivedOn")) {
            return (Date) this.getParameters().get("ReceivedOn");
        }

        return null;
    }

    public void setReceivedOn(Date value) {
        this.set("ReceivedOn", value);
    }

    @Nullable
    public String getSubmittedBy() {
        if (this.getParameters().containsKey("SubmittedBy")) {
            return (String) this.getParameters().get("SubmittedBy");
        }

        return null;
    }

    public void setSubmittedBy(String value) {
        this.set("SubmittedBy", value);
    }

    public boolean getValidateBeforeCreate() {
        if (this.getParameters().containsKey("ValidateBeforeCreate")) {
            return (boolean) this.getParameters().get("ValidateBeforeCreate");
        }

        return false;
    }

    public void setValidateBeforeCreate(boolean value) {
        this.set("ValidateBeforeCreate", value);
    }

    @Override
    public String getRequestBody() {
        this.set("Attachments", getAttachments());
        this.set("Bcc", getBcc());
        this.set("Body", getBody());
        this.set("From", getFrom());
        this.set("Cc", getCc());
        this.set("Subject", getSubject());
        this.set("To", getTo());
        this.set("ExtraProperties", getExtraProperties());
        this.set("Importance", getImportance());
        this.set("MessageId", getMessageId());
        this.set("ReceivedOn", getReceivedOn());
        this.set("SubmittedBy", getSubmittedBy());
        this.set("ValidateBeforeCreate", getValidateBeforeCreate());
        return getSoapBody();
    }

}
