//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/24/2015.
// */
//public final class DeleteEntityRequest extends OrganizationRequest {
//
//    public DeleteEntityRequest() {
//        this.setRequestName("DeleteEntity");
//        this.setLogicalName(null);
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
//}
