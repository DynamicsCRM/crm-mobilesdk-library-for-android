package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.Entity;
import com.microsoft.xrm.sdk.OptionSetValue;
import com.microsoft.xrm.sdk.OrganizationRequest;
import com.microsoft.xrm.sdk.OrganizationResponse;

/**
 * Created on 3/27/2015.
 */
public final class WinQuoteRequest extends OrganizationRequest {

    public WinQuoteRequest() {
        this.setResponseType(new OrganizationResponse());
        this.setRequestName("WinQuote");
    }

    @Nullable
    public Entity getQuoteClose() {
        if (this.getParameters().containsKey("QuoteClose")) {
            return (Entity) this.getParameters().get("QuoteClose");
        }

        return null;
    }

    public void setQuoteClose(Entity value) {
        this.set("QuoteClose", value);
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
        this.set("QuoteClose", getQuoteClose());
        this.set("Status", getStatus());
        return getSoapBody();
    }

}
