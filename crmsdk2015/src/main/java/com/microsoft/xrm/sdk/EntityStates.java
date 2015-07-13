package com.microsoft.xrm.sdk;

/**
 * Created on 3/6/2015.
 */
enum EntityStates {
    Detached(1),
    Unchanged(2),
    Added(4),
    Deleted(8),
    Modified(16);

    private int mValue;

    EntityStates(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return this.mValue;
    }

    public static int getValue(String value) {
        return EntityStates.valueOf(value.replace(" ", "")).getValue();
    }

    public static EntityStates valueOf(int value) {
        switch (value) {
            case 1:
                return Detached;
            case 2:
                return Unchanged;
            case 4:
                return Added;
            case 8:
                return Deleted;
            case 16:
                return Modified;

        }

        return null;
    }
}
