package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/27/2015.
 */
public final class CreateInstanceRequest extends OrganizationRequest {

    public CreateInstanceRequest() {
        this.setResponseType(new CreateInstanceResponse());
        this.setRequestName("CreateInstance");
    }

    public int getCount() {
        if (this.getParameters().containsKey("Count")) {
            return (int) this.getParameters().get("Count");
        }

        return 0;
    }

    public void setCount(int value) {
        this.set("Count", value);
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
        this.set("Count", getCount());
        this.set("Target", getTarget());
        return getSoapBody();
    }
}
