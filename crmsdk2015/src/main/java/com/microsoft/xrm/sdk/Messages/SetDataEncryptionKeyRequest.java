//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/24/2015.
// */
//public final class SetDataEncryptionKeyRequest extends OrganizationRequest {
//
//    public SetDataEncryptionKeyRequest() {
//        this.setRequestName("SetDataEncryptionKey");
//        this.setEncryptionKey(null);
//        this.setChangeEncryptionKey(false);
//    }
//
//    @Nullable
//    public String getEncryptionKey() {
//        if (this.getParameters().containsKey("EncryptionKey")) {
//            return this.getParameters().get("EncryptionKey").toString();
//        }
//
//        return null;
//    }
//
//    public void setEncryptionKey(String value) {
//        this.set("EncryptionKey", value);
//    }
//
//    public boolean getChangeEncryptionKey() {
//        if (this.getParameters().containsKey("ChangeEncryptionKey")) {
//            return (boolean) this.getParameters().get("ChangeEncryptionKey");
//        }
//
//        return false;
//    }
//
//    public void setChangeEncryptionKey(boolean value) {
//        this.set("ChangeEncryptionKey", value);
//    }
//}
