package com.microsoft.xrm.sdk.Metadata;

import com.microsoft.xrm.sdk.Utils;

import java.util.UUID;

/**
 * Created on 3/26/2015.
 */
public abstract class MetadataBase {

    private UUID id;
    private Boolean hasChanged;

    public UUID getMetadataId() {
        return this.id;
    }

    public void setMetadataId(UUID value) {
        this.id = value;
    }

    public Boolean getHasChanged() {
        return this.hasChanged;
    }

    public void setHasChanged(Boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

    protected String toValueXml()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.objectToXml(hasChanged, "h:HasChanged", true));
        stringBuilder.append(Utils.objectToXml(id, "h:MetadataId", true));
        return stringBuilder.toString();
    }
}
