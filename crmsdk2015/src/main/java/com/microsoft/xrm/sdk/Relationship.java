package com.microsoft.xrm.sdk;

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

    String toValueXml() {
        return Utils.objectToXml(PrimaryEntityRole, "a:PrimaryEntityRole", true) +
            Utils.objectToXml(SchemaName, "a:SchemaName", true);
    }
}
