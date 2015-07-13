//package com.microsoft.xrm.sdk.Messages;
//
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/24/2015.
// */
//public final class RetrieveAllOptionSetsRequest extends OrganizationRequest {
//
//    public RetrieveAllOptionSetsRequest() {
//        this.setRequestName("RetrieveAllOptionSets");
//        this.setRetrieveAsIfPublished(false);
//    }
//
//    public boolean getRetrieveAsIfPublished() {
//        if (this.getParameters().containsKey("RetrieveAsIfPublished")) {
//            return (boolean) this.getParameters().get("RetrieveAsIfPublished");
//        }
//
//        return false;
//    }
//
//    public void setRetrieveAsIfPublished(boolean value) {
//        this.set("RetrieveAsIfPublished", value);
//    }
//}
