package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.Date;

/**
 * Created on 3/27/2015.
 */
public final class DeleteOpenInstancesRequest extends OrganizationRequest {

    public DeleteOpenInstancesRequest() {
        this.setResponseType(new DeleteOpenInstancesResponse());
        this.setRequestName("DeleteOpenInstances");
    }

    public int getStateOfPastInstances() {
        if (this.getParameters().containsKey("StateOfPastInstances")) {
            return (int) this.getParameters().get("StateOfPastInstances");
        }

        return 0;
    }
    public void setStateOfPastInstances(int value) {
        this.set("StateOfPastInstances", value);
    }

    @Nullable
    public Date getSeriesEndDate() {
        if (this.getParameters().containsKey("SeriesEndDate")) {
            return (Date) this.getParameters().get("SeriesEndDate");
        }

        return null;
    }

    public void setSeriesEndDate(Date value) {
        this.set("SeriesEndDate", value);
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
        this.set("SeriesEndDate", getSeriesEndDate());
        this.set("StateOfPastInstances", getStateOfPastInstances());
        this.set("Target", getTarget());
        return getSoapBody();
    }
}
