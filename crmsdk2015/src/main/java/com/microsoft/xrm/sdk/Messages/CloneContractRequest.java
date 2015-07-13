package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/26/2015.
 */
public final class CloneContractRequest extends OrganizationRequest {

    public CloneContractRequest() {
        this.setResponseType(new CloneContractResponse());
        this.setRequestName("CloneContract");
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

    public boolean getIncludeCanceledLines() {
        if (this.getParameters().containsKey("IncludeCanceledLines")) {
            return (boolean) this.getParameters().get("IncludeCanceledLines");
        }

        return false;
    }

    public void setIncludeCanceledLines(boolean value) {
        this.set("IncludeCanceledLines", value);
    }

    @Override
    public String getRequestBody() {
        this.set("ContractId", getContractId());
        this.set("IncludeCanceledLines", getIncludeCanceledLines());
        return getSoapBody();
    }
}
