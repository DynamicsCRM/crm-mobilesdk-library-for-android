package com.microsoft.xrm.sdk.Metadata;

import com.microsoft.xrm.sdk.Utils;

import java.util.UUID;

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

    protected String toValueXml() {
        return Utils.objectToXml(hasChanged, "h:HasChanged", true) +
            Utils.objectToXml(id, "h:MetadataId", true);
    }
}
