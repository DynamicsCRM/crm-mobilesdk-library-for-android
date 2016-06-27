//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/24/2015.
// */
//public final class DeleteAttributeRequest extends OrganizationRequest {
//
//    public DeleteAttributeRequest() {
//        this.setRequestName("DeleteAttribute");
//        this.setLogicalName(null);
//        this.setEntityLogicalName(null);
//    }
//
//    @Nullable
//    public String getLogicalName() {
//        if (this.getParameters().containsKey("LogicalName")) {
//            return this.getParameters().get("LogicalName").toString();
//        }
//        return null;
//    }
//
//    public void setLogicalName(String value) {
//        this.set("LogicalName", value);
//    }
//
//    @Nullable
//    public String getEntityLogicalName() {
//        if (this.getParameters().containsKey("EntityLogicalName")) {
//            return this.getParameters().get("EntityLogicalName").toString();
//        }
//        return null;
//    }
//
//    public void setEntityLogicalName(String value) {
//        this.set("EntityLogicalName", value);
//    }
//}
