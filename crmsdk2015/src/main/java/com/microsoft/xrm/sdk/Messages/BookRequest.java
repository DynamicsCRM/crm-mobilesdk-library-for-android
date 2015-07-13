package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/26/2015.
 */
public final class BookRequest extends OrganizationRequest {

    public BookRequest() {
        this.setResponseType(new BookResponse());
        this.setRequestName("Book");
    }

    @Nullable
    public Entity getTarget() {
        if (this.getParameters().containsKey("Target")) {
            return (Entity) this.getParameters().get("Target");
        }

        return null;
    }

    public void setTarget(Entity value) {
        this.set("Target", value);
    }

    public boolean getReturnNotifications() {
        return this.getParameters().containsKey("ReturnNotifications") && (boolean) this.getParameters().get("ReturnNotifications");
    }

    public void setReturnNotifications(boolean value) {
        this.set("ReturnNotifications", value);
    }

    @Override
    public String getRequestBody() {
        this.set("Target", getTarget());
        this.set("ReturnNotifications", getReturnNotifications());
        return getSoapBody();
    }
}
