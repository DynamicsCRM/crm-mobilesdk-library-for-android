package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/27/2015.
 */
public final class RescheduleRequest extends OrganizationRequest {

    public RescheduleRequest() {
        this.setResponseType(new RescheduleResponse());
        this.setRequestName("Reschedule");
    }

    public RescheduleRequest(Entity target) {
        this();
        setTarget(target);
    }

    public RescheduleRequest(Entity target, boolean returnNotifications) {
        this(target);
        setReturnNotifications(returnNotifications);
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
        if (this.getParameters().containsKey("ReturnNotifications")) {
            return (boolean) this.getParameters().get("ReturnNotifications");
        }

        return false;
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
