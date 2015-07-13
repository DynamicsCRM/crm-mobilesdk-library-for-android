package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationRequest;

import java.util.UUID;

/**
 * Created on 3/27/2015.
 */
public final class RemovePrivilegeRoleRequest extends OrganizationRequest {

    public RemovePrivilegeRoleRequest() {
        this.setResponseType(new RemovePrivilegeRoleResponse());
        this.setRequestName("RemovePrivilegeRole");
    }

    public UUID getRoleId() {
        if (this.getParameters().containsKey("RoleId")) {
            return (UUID) this.getParameters().get("RoleId");
        }

        return new UUID(0L, 0L);
    }

    public void setRoleId(UUID value) {
        this.set("RoleId", value);
    }

    public UUID getPrivilegeId() {
        if (this.getParameters().containsKey("PrivilegeId")) {
            return (UUID) this.getParameters().get("PrivilegeId");
        }

        return new UUID(0L, 0L);
    }

    public void setPrivilegeId(UUID value) {
        this.set("PrivilegeId", value);
    }

    @Override
    public String getRequestBody() {
        this.set("RoleId", getRoleId());
        this.set("PrivilegeId", getPrivilegeId());
        return getSoapBody();
    }
}
