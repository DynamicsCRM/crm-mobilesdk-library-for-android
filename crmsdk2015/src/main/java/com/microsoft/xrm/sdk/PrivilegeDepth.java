package com.microsoft.xrm.sdk;

import android.support.annotation.Nullable;

/**
 * Created on 3/30/2015.
 */
public enum PrivilegeDepth {
    Basic(0),
    Local(1),
    Deep(2),
    Global(3);

    private int mValue;

    PrivilegeDepth(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return this.mValue;
    }

    public static int getValue(String value) {
        return PrivilegeDepth.valueOf(value.replace(" ", "")).getValue();
    }

    @Nullable
    public static PrivilegeDepth valueOf(int value) {
        switch (value) {
            case 0:
                return Basic;
            case 1:
                return Local;
            case 2:
                return Deep;
            case 3:
                return Global;
            default:
                return null;
        }
    }

}
