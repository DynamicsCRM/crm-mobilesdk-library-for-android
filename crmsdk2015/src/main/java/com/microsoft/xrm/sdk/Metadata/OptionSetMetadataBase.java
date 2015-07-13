package com.microsoft.xrm.sdk.Metadata;

import com.microsoft.xrm.sdk.BooleanManagedProperty;
import com.microsoft.xrm.sdk.Label;

/**
 * Created on 3/26/2015.
 */
public abstract class OptionSetMetadataBase extends MetadataBase {

    private Label description;
    private Label displayName;
    private Boolean isCustomOptionSet;
    private Boolean isGlobal;
    private Boolean isManaged;
    private String name;
    private OptionSetType optionSetType;
    private BooleanManagedProperty isCustomizable;
    private String introducedVersion;

    public Label getDescription() {
        return description;
    }

    public Label getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name;
    }

    public String getIntroducedVersion() {
        return introducedVersion;
    }

    public OptionSetType getOptionSetType() {
        return optionSetType;
    }

    public BooleanManagedProperty getIsCustomizable() {
        return isCustomizable;
    }

    public Boolean getIsCustomOptionSet() {
        return this.isCustomOptionSet;
    }

    public Boolean getIsGlobal() {
        return this.isGlobal;
    }

    public Boolean getIsManaged() {
        return this.isManaged;
    }

    public void setIsManaged(Boolean isManaged) {
        this.isManaged = isManaged;
    }

    public void setDescription(Label description) {
        this.description = description;
    }

    public void setDisplayName(Label displayName) {
        this.displayName = displayName;
    }

    public void setIntroducedVersion(String introducedVersion) {
        this.introducedVersion = introducedVersion;
    }

    public void setIsCustomizable(BooleanManagedProperty isCustomizable) {
        this.isCustomizable = isCustomizable;
    }

    public void setIsCustomOptionSet(Boolean isCustomOptionSet) {
        this.isCustomOptionSet = isCustomOptionSet;
    }

    public void setIsGlobal(Boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOptionSetType(OptionSetType optionSetType) {
        this.optionSetType = optionSetType;
    }

    public Boolean isCustomOptionSet() {
        return isCustomOptionSet;
    }
}
