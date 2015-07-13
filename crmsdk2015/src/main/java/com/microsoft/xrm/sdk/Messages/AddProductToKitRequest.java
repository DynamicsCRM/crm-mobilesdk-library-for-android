package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/26/2015.
 */
public final class AddProductToKitRequest extends OrganizationRequest {

    public AddProductToKitRequest() {
        this.setResponseType(new AddProductToKitResponse());
        this.setRequestName("AddProductToKit");
    }

    public UUID getKitId() {
        if (this.getParameters().containsKey("KitId")) {
            return (UUID) this.getParameters().get("KitId");
        }

        return new UUID(0L, 0L);
    }

    public void setKitId(UUID value) {
        this.set("KitId", value);
    }

    public UUID getProductId() {
        if (this.getParameters().containsKey("ProductId")) {
            return (UUID) this.getParameters().get("ProductId");
        }

        return new UUID(0L, 0L);
    }

    public void setProductId(UUID value) {
        this.set("ProductId", value);
    }

    @Override
    public String getRequestBody() {
        this.set("KitId", getKitId());
        this.set("ProductId", getProductId());
        return getSoapBody();
    }
}
