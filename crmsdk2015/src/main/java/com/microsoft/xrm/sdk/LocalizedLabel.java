package com.microsoft.xrm.sdk;

import com.microsoft.xrm.sdk.Metadata.MetadataBase;

public final class LocalizedLabel extends MetadataBase {

    private String label;
    private int languageCode;
    private Boolean isManaged;

    public LocalizedLabel() {}

    public LocalizedLabel(String label, int languageCode) {
        this.label = label;
        this.languageCode = languageCode;
    }

    public String getLabel() {
        return label;
    }

    public int getLanguageCode() {
        return languageCode;
    }

    public Boolean getIsManaged() {
        return this.isManaged;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setLanguageCode(int languageCode) {
        this.languageCode = languageCode;
    }

    public void setIsManaged(Boolean isManaged) {
        this.isManaged = isManaged;
    }

    protected String toValueXml()
    {
        return super.toValueXml() +
            Utils.objectToXml(this.isManaged, "a:IsManaged", true) +
            Utils.objectToXml(this.label, "a:Label", true) +
            Utils.objectToXml(this.languageCode, "a:LanguageCode", true);
    }
}
