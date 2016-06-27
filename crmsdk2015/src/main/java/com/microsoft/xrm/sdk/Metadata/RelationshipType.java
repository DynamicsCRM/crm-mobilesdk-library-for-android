package com.microsoft.xrm.sdk.Metadata;

/**
 * Created on 3/26/2015.
 */
public enum RelationshipType {

    Default(0),
    OneToManyRelationship(0),
    ManyToManyRelationship(1);

    private int mValue;

    RelationshipType(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return this.mValue;
    }

    public static int getValue(String value) {
        return RelationshipType.valueOf(value.replace(" ", "").toUpperCase()).getValue();
    }

    public static RelationshipType valueOf(int value) {
        switch (value) {
            case 0:
                return OneToManyRelationship;
            case 1:
                return ManyToManyRelationship;
            default:
                return Default;
        }
    }
}
