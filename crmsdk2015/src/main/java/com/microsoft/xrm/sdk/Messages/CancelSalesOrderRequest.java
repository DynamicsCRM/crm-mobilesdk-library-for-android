package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OptionSetValue;
import com.microsoft.xrm.sdk.OrganizationRequest;

/**
 * Created on 3/26/2015.
 */
public final class CancelSalesOrderRequest extends OrganizationRequest {

    public CancelSalesOrderRequest() {
        this.setResponseType(new CancelSalesOrderResponse());
        this.setRequestName("CancelSalesOrder");
    }

    @Nullable
    public Entity getOrderClose() {
        if (this.getParameters().containsKey("OrderClose")) {
            return (Entity) this.getParameters().get("OrderClose");
        }

        return null;
    }

    public void setOrderClose(Entity value) {
        this.set("OrderClose", value);
    }

    @Nullable
    public OptionSetValue getStatus() {
        if (this.getParameters().containsKey("Status")) {
            return (OptionSetValue) this.getParameters().get("Status");
        }

        return null;
    }

    public void setStatus(OptionSetValue value) {
        this.set("Status", value);
    }

    @Override
    public String getRequestBody() {
        this.set("OrderClose", getOrderClose());
        this.set("Status", getStatus());
        return getSoapBody();
    }
}
