package com.microsoft.xrm.sdk;

/**
 * Created on 3/26/2015.
 */
public final class Label {

    private LocalizedLabelCollection localizedLabels;
    private LocalizedLabel userLocalizedLabel;

    public LocalizedLabel getUserLocalizedLabel() {
        return userLocalizedLabel;
    }

    public LocalizedLabelCollection getLocalizedLabels() {
        if (this.localizedLabels == null) {
            this.localizedLabels = new LocalizedLabelCollection();
        }

        return localizedLabels;
    }

    String toValueXml()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.objectToXml(this.localizedLabels, "a:LocalizedLabels", true));
        stringBuilder.append(Utils.objectToXml(this.userLocalizedLabel, "a:UserLocalizedLabel", true));
        return stringBuilder.toString();
    }
}
