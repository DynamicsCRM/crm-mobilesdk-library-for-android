package com.microsoft.xrm.sdk;

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
        return Utils.objectToXml(this.localizedLabels, "a:LocalizedLabels", true) +
            Utils.objectToXml(this.userLocalizedLabel, "a:UserLocalizedLabel", true);
    }
}
