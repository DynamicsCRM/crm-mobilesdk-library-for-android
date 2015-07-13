//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/24/2015.
// */
//public final class UpdateAttributeRequest extends OrganizationRequest {
//
//    public UpdateAttributeRequest() {
//        this.setRequestName("UpdateAttribute");
//        this.setAttribute(null);
//        this.setEntityName(null);
//        this.setMergeLabels(false);
//    }
//
//    @Nullable
//    public AttributeMetadata getAttribute() {
//        if (this.getParameters().containsKey("Attribute")) {
//            return (AttributeMetadata) this.getParameters().get("Attribute");
//        }
//        return null;
//    }
//
//    public void setAttribute(AttributeMetadata value) {
//        this.set("Attribute", value);
//    }
//
//    @Nullable
//    public String getEntityName() {
//        if (this.getParameters().containsKey("EntityName")) {
//            return this.getParameters().get("EntityName").toString();
//        }
//
//        return null;
//    }
//
//    public void setEntityName(String value) {
//        this.set("EntityName", value);
//    }
//
//    public boolean getMergeLabels() {
//        return this.getParameters().containsKey("MergeLabels") && (boolean) this.getParameters().get("MergeLabels");
//    }
//
//    public void setMergeLabels(boolean value) {
//        this.set("MergeLabels", value);
//    }
//
//    @Nullable
//    public String getSolutionUniqueName() {
//        if (this.getParameters().containsKey("SolutionUniqueName")) {
//            return this.getParameters().get("SolutionUniqueName").toString();
//        }
//        return null;
//    }
//
//    public void setSolutionUniqueName(String value) {
//        this.set("SolutionUniqueName", value);
//    }
//}
