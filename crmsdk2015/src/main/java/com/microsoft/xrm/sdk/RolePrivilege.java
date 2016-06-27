package com.microsoft.xrm.sdk;

import java.util.UUID;

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

    String toValueXml() {
        return Utils.objectToXml(businessUnitId, "g:BusinessUnitId", true) +
            Utils.objectToXml(depth, "g:Depth", true) +
            Utils.objectToXml(privilegeId, "g:PrivilegeId", true);
    }
}
