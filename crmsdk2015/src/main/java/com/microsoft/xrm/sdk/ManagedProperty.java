package com.microsoft.xrm.sdk;

public abstract class ManagedProperty<T> {

    private T value;
    private boolean canBeChanged;
    private String logicalName;

    protected ManagedProperty() {
        this(null);
    }

    protected ManagedProperty(String managedPropertyLogicalName) {
        this.setLogicalName(managedPropertyLogicalName);
        this.setCanBeChanged(true);
    }

    public T getValue() {
        return value;
    }

    public boolean getCanBeChanged() {
        return this.canBeChanged;
    }

    public String getLogicalName() {
        return logicalName;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setCanBeChanged(boolean canBeChanged) {
        this.canBeChanged = canBeChanged;
    }

    public void setLogicalName(String logicalName) {
        this.logicalName = logicalName;
    }

    protected String ToValueXml() {
        return Utils.objectToXml(this.canBeChanged, "a:CanBeChanged", true) +
        Utils.objectToXml(this.logicalName, "a:ManagedPropertyLogicalName", true);
    }
}
