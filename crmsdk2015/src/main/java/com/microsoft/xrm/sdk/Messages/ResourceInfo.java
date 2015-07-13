package com.microsoft.xrm.sdk.Messages;

import java.util.UUID;

/**
 * Created on 3/31/2015.
 */
public final class ResourceInfo {

    private String displayName;
    private String entityName;
    private UUID id;

    public String getDisplayName() {
        return displayName;
    }

    public String getEntityName() {
        return entityName;
    }

    public UUID getId() {
        return id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

//    static ResourceInfo LoadFromXml(XElement item)
//    {
//        ResourceInfo resourceInfo = new ResourceInfo()
//        {
//            Id = Util.LoadFromXml<Guid>(item.Element(Util.ns.g + "Id")),
//            DisplayName = item.Element(Util.ns.g + "DisplayName").Value,
//            EntityName = item.Element(Util.ns.g + "EntityName").Value
//        };
//        return resourceInfo;
//    }
}
