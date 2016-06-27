package com.microsoft.xrm.sdk.Metadata;

import com.microsoft.xrm.sdk.BooleanManagedProperty;

/**
 * Created on 3/26/2015.
 */
public abstract class RelationshipMetadataBase extends MetadataBase {

    private Boolean isCustomRelationship;
    private Boolean isValidForAdvancedFind;
    private String schemaName;
    private SecurityTypes securityTypes;
    private Boolean isManaged;
    private BooleanManagedProperty isCustomizable;
    private RelationshipType type;
    private String introducedVersion;

    /// <summary>
    /// Initializes a new instance of the <see cref="T:Microsoft.Xrm.Sdk.Metadata.RelationshipMetadataBase"/> class
    /// </summary>
    protected RelationshipMetadataBase()
    {
    }

    /// <summary>
    /// Initializes a new instance of the <see cref="T:Microsoft.Xrm.Sdk.Metadata.RelationshipMetadataBase"/> class
    /// </summary>
    /// <param name="type">Type: <see cref="T:Microsoft.Xrm.Sdk.Metadata.RelationshipType"/>
    /// The type of relationship.</param>
    protected RelationshipMetadataBase(RelationshipType type)
    {
        this.type = type;
    }

    public BooleanManagedProperty getIsCustomizable() {
        return isCustomizable;
    }

    public String getIntroducedVersion() {
        return introducedVersion;
    }

    public RelationshipType getType() {
        return type;
    }

    public SecurityTypes getSecurityTypes() {
        return securityTypes;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public Boolean getIsCustomRelationship() {
        return this.isCustomRelationship;
    }

    public Boolean getIsValidForAdvancedFind() {
        return this.isValidForAdvancedFind;
    }

    public Boolean getIsManaged() {
        return this.isManaged;
    }

    public void setIsCustomizable(BooleanManagedProperty isCustomizable) {
        this.isCustomizable = isCustomizable;
    }

    public void setIntroducedVersion(String introducedVersion) {
        this.introducedVersion = introducedVersion;
    }

    public void setIsCustomRelationship(Boolean isCustomRelationship) {
        this.isCustomRelationship = isCustomRelationship;
    }

    public void setIsManaged(Boolean isManaged) {
        this.isManaged = isManaged;
    }

    public void setIsValidForAdvancedFind(Boolean isValidForAdvancedFind) {
        this.isValidForAdvancedFind = isValidForAdvancedFind;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public void setSecurityTypes(SecurityTypes securityTypes) {
        this.securityTypes = securityTypes;
    }

    public void setType(RelationshipType type) {
        this.type = type;
    }
}
