package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/24/2015.
 */
public final class CreateRequest extends OrganizationRequest {

    public CreateRequest() {
        this.setRequestName("Create");
        this.setResponseType(new CreateResponse());
        this.setTarget(null);
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
        this.set("Target", getTarget());
        return getSoapBody();
    }
}
