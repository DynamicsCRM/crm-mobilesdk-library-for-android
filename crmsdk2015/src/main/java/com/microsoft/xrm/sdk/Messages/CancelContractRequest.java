package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.OptionSetValue;
import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.Date;
import java.util.UUID;

/**
 * Created on 3/26/2015.
 */
public final class CancelContractRequest extends OrganizationRequest {

    public CancelContractRequest() {
        this.setResponseType(new CancelContractResponse());
        this.setRequestName("CancelContract");
    }

    @Nullable
    public Date getCancelDate() {
        if (this.getParameters().containsKey("CancelDate")) {
            return (Date) this.getParameters().get("CancelDate");
        }

        return null;
    }

    public void setCancelDate(Date value) {
        this.set("CancelDate", value);
    }

    public UUID getContractId() {
        if (this.getParameters().containsKey("ContractId")) {
            return (UUID) this.getParameters().get("ContractId");
        }

        return new UUID(0L, 0L);
    }

    public void setContractId(UUID value) {
        this.set("ContractId", value);
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
        this.set("CancelDate", getCancelDate());
        this.set("ContractId", getContractId());
        this.set("Status", getStatus());
        return getSoapBody();
    }
}
