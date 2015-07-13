package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/27/2015.
 */
public final class ValidateRecurrenceRuleRequest extends OrganizationRequest {

    public ValidateRecurrenceRuleRequest() {
        this.setRequestName("ValidateRecurrenceRule");
        this.setResponseType(new ValidateRecurrenceRuleResponse());
    }

    public ValidateRecurrenceRuleRequest(Entity target) {
        this();
        this.setTarget(target);
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
