package com.microsoft.xrm.sdk;

/**
 * Created on 3/6/2015.
 */
public class Relationship {

    private EntityRole PrimaryEntityRole;
    private String SchemaName;

    public Relationship() {

    }

    public Relationship(String schemaName) {
        this.SchemaName = schemaName;
    }

    public String getSchemaName() {
        return this.SchemaName;
    }

    public void setSchemaName(String value) {
        this.SchemaName = value;
    }

    public EntityRole getPrimaryEntityRole() {
        return PrimaryEntityRole;
    }

    public void setPrimaryEntityRole(EntityRole value) {
        this.PrimaryEntityRole = value;
    }

    String toValueXml()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.objectToXml(PrimaryEntityRole, "a:PrimaryEntityRole", true));
        stringBuilder.append(Utils.objectToXml(SchemaName, "a:SchemaName", true));
        return stringBuilder.toString();
    }
}
