package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.Date;

/**
 * Created on 3/27/2015.
 */
public final class CreateExceptionRequest extends OrganizationRequest {

    public CreateExceptionRequest() {
        this.setRequestName("CreateException");
        this.setResponseType(new CreateExceptionResponse());
    }

    public boolean getIsDeleted() {
        if (this.getParameters().containsKey("IsDeleted")) {
            return (boolean) this.getParameters().get("IsDeleted");
        }

        return false;
    }

    public void setIsDeleted(boolean value) {
        this.set("IsDeleted", value);
    }

    @Nullable
    public Date getOriginalStartDate() {
        if (this.getParameters().containsKey("OriginalStartDate")) {
            return (Date) this.getParameters().get("OriginalStartDate");
        }

        return null;
    }

    public void setOriginalStartDate(Date value) {
        this.set("OriginalStartDate", value);
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
        this.set("IsDeleted", getIsDeleted());
        this.set("OriginalStartDate", getOriginalStartDate());
        this.set("Target", getTarget());
        return getSoapBody();
    }
}
