package com.microsoft.xrm.sdk;

import com.microsoft.xrm.sdk.Metadata.MetadataBase;

/**
 * Created on 3/26/2015.
 */
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toValueXml());
        stringBuilder.append(Utils.objectToXml(this.isManaged, "a:IsManaged", true));
        stringBuilder.append(Utils.objectToXml(this.label, "a:Label", true));
        stringBuilder.append(Utils.objectToXml(this.languageCode, "a:LanguageCode", true));
        return stringBuilder.toString();
    }
}
