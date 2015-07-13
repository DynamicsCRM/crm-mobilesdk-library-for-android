package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/26/2015.
 */
public final class AddRecurrenceRequest extends OrganizationRequest {

    public AddRecurrenceRequest() {
        this.setRequestName("AddRecurrence");
        this.setResponseType(new AddRecurrenceResponse());
    }

    public UUID getAppointmentId() {
        if(this.getParameters().containsKey("AppointmentId")) {
            return (UUID) this.getParameters().get("AppointmentId");
        }

        return new UUID(0L, 0L);
    }

    public void setAppointmentId(UUID value) {
        this.set("AppointmentId", value);
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

    @Override
    public String getRequestBody() {
        this.set("AppointmentId", getAppointmentId());
        this.set("Target", getTarget());
        return getSoapBody();
    }
}
