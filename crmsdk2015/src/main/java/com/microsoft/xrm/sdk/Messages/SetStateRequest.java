package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.EntityReference;
import com.microsoft.xrm.sdk.OptionSetValue;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/27/2015.
 */
public final class SetStateRequest extends OrganizationRequest {

    public SetStateRequest() {
        this.setResponseType(new SetStateResponse());
        this.setRequestName("SetState");
    }

    public static SetStateRequest build() {
        return new SetStateRequest();
    }

    @Nullable
    public EntityReference getEntityMoniker() {
        if (this.getParameters().containsKey("EntityMoniker")) {
            return (EntityReference) this.getParameters().get("EntityMoniker");
        }

        return null;
    }

    public SetStateRequest setEntityMoniker(EntityReference value) {
        this.set("EntityMoniker", value);
        return this;
    }

    @Nullable
    public OptionSetValue getState() {
        if (this.getParameters().containsKey("State")) {
            return (OptionSetValue) this.getParameters().get("State");
        }

        return null;
    }

    public SetStateRequest setState(OptionSetValue value) {
        this.set("State", value);
        return this;
    }

    @Nullable
    public OptionSetValue getStatus() {
        if (this.getParameters().containsKey("Status")) {
            return (OptionSetValue) this.getParameters().get("Status");
        }

        return null;
    }

    public SetStateRequest setStatus(OptionSetValue value) {
        this.set("Status", value);
        return this;
    }

    @Override
    public String getRequestBody() {
        this.set("EntityMoniker", getEntityMoniker());
        this.set("State", getState());
        this.set("Status", getStatus());
        return getSoapBody();
    }

}
