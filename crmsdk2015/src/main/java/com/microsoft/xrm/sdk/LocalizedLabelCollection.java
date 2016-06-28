package com.microsoft.xrm.sdk;

import java.util.List;

public final class LocalizedLabelCollection extends DataCollection<LocalizedLabel> {

    public LocalizedLabelCollection() {

    }

    public LocalizedLabelCollection(List<LocalizedLabel> list) {
        super(list);
    }

    String toValueXml()
    {
        return Utils.objectToXml(this.toArray(), "a:LocalizedLabel", true);
    }
}
