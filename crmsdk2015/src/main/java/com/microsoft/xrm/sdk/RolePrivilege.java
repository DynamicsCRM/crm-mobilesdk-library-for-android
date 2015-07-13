package com.microsoft.xrm.sdk;

import java.util.UUID;

/**
 * Created on 3/31/2015.
 */
public final class RolePrivilege {

    private UUID businessUnitId;
    private PrivilegeDepth depth;
    private UUID privilegeId;

    public RolePrivilege(int depth, UUID privilegeId) {
        this.depth = PrivilegeDepth.valueOf(depth);
        this.privilegeId = privilegeId;
    }

    public PrivilegeDepth getDepth() {
        return depth;
    }

    public UUID getBusinessUnitId() {
        return businessUnitId;
    }

    public UUID getPrivilegeId() {
        return privilegeId;
    }

    public void setBusinessUnitId(UUID businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public void setDepth(PrivilegeDepth depth) {
        this.depth = depth;
    }

    public void setPrivilegeId(UUID privilegeId) {
        this.privilegeId = privilegeId;
    }

    String toValueXml()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.objectToXml(businessUnitId, "g:BusinessUnitId", true));
        stringBuilder.append(Utils.objectToXml(depth, "g:Depth", true));
        stringBuilder.append(Utils.objectToXml(privilegeId, "g:PrivilegeId", true));
        return stringBuilder.toString();
    }
}
