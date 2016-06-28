package com.microsoft.xrm.sdk;

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

    protected String toValueXml() {
        return super.ToValueXml() + Utils.objectToXml(this.getValue(), "a:Value", true);
    }
}
