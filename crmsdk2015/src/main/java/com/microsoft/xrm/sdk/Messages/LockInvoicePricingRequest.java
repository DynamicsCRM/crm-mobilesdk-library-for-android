package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/27/2015.
 */
public final class LockInvoicePricingRequest extends OrganizationRequest {

    public LockInvoicePricingRequest() {
        this.setResponseType(new LockInvoicePricingResponse());
        this.setRequestName("LockInvoicePricing");
    }

    public UUID getInvoiceId() {
        if (this.getParameters().containsKey("InvoiceId")) {
            return (UUID) this.getParameters().get("InvoiceId");
        }

        return new UUID(0L, 0L);
    }

    public void setInvoiceId(UUID value) {
        this.set("InvoiceId", value);
    }

    @Override
    public String getRequestBody() {
        this.set("InvoiceId", getInvoiceId());
        return getSoapBody();
    }
}
