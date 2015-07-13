package com.microsoft.xrm.sdk;

/**
 * Created on 3/26/2015.
 */
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

    protected String ToValueXml()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.objectToXml(this.canBeChanged, "a:CanBeChanged", true));
        stringBuilder.append(Utils.objectToXml(this.logicalName, "a:ManagedPropertyLogicalName", true));
        return stringBuilder.toString();
    }
}
