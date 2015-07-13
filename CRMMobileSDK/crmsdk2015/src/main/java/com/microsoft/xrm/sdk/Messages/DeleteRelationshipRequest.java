//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/24/2015.
// */
//public final class DeleteRelationshipRequest extends OrganizationRequest {
//
//    public DeleteRelationshipRequest() {
//        this.setRequestName("DeleteRelationship");
//        this.setName(null);
//    }
//
//    @Nullable
//    public String getName() {
//        if (this.getParameters().containsKey("Name")) {
//            return this.getParameters().get("Name").toString();
//        }
//        return null;
//    }
//
//    public void setName(String value) {
//        this.set("Name", value);
//    }
//}
