package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/27/2015.
 */
public final class LockSalesOrderPricingRequest extends OrganizationRequest {

    public LockSalesOrderPricingRequest() {
        this.setResponseType(new LockSalesOrderPricingResponse());
        this.setRequestName("LockSalesOrderPricing");
    }

    public UUID getSalesOrderId() {
        if (this.getParameters().containsKey("SalesOrderId")) {
            return (UUID) this.getParameters().get("SalesOrderId");
        }

        return new UUID(0L, 0L);
    }

    public void setSalesOrderId(UUID value) {
        this.set("SalesOrderId", value);
    }

    @Override
    public String getRequestBody() {
        this.set("SalesOrderId", getSalesOrderId());
        return getSoapBody();
    }
}
