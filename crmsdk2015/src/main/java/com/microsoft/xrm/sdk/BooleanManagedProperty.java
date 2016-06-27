package com.microsoft.xrm.sdk;

/**
 * Created on 3/26/2015.
 */
public class BooleanManagedProperty extends ManagedProperty<Boolean> {

    public BooleanManagedProperty() {
        this(false);
    }

    public BooleanManagedProperty(boolean value) {
        this(value, null);
    }

    BooleanManagedProperty(boolean value, String logicalName) {
        super(logicalName);
        this.setValue(value);
    }

    protected String toValueXml()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.ToValueXml());
        stringBuilder.append(Utils.objectToXml(this.getValue(), "a:Value", true));
        return stringBuilder.toString();
    }
}
