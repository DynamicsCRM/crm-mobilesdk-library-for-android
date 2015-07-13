package com.microsoft.xrm.sdk.Metadata;

/**
 * Created on 3/26/2015.
 */
public enum SecurityTypes {

    None(0),
    Append(1),
    ParentChild(2),
    Pointer(4),
    Inheritance(8);

    private int mValue;

    SecurityTypes(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return this.mValue;
    }

    public static int getValue(String value) {
        return SecurityTypes.valueOf(value.replace(" ", "")).getValue();
    }

    public static SecurityTypes valueOf(int value) {
        switch (value) {
            case 0:
                return None;
            case 1:
                return Append;
            case 2:
                return ParentChild;
            case 4:
                return Pointer;
            case 8:
                return Inheritance;
        }

        return null;
    }
}
