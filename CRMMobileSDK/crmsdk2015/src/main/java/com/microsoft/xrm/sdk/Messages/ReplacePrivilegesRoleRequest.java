package com.microsoft.xrm.sdk.Messages;

import android.support.annotation.Nullable;

import com.microsoft.xrm.sdk.OrganizationRequest;
import com.microsoft.xrm.sdk.RolePrivilege;

import java.util.UUID;

/**
 * Created on 3/27/2015.
 */
public final class ReplacePrivilegesRoleRequest extends OrganizationRequest {

    public ReplacePrivilegesRoleRequest() {
        this.setRequestName("ReplacePrivilegesRole");
        this.setResponseType(new ReplacePrivilegesRoleResponse());
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

    @Nullable
    public RolePrivilege[] getPrivileges() {
        if (this.getParameters().containsKey("Privileges")) {
            return (RolePrivilege[]) this.getParameters().get("Privileges");
        }

        return null;
    }

    public void setPrivileges(RolePrivilege[] value) {
        this.set("Privileges", value);
    }

    @Override
    public String getRequestBody() {
        this.set("RoleId", getRoleId());
        this.set("Privileges", getPrivileges());
        return getSoapBody();
    }
}
