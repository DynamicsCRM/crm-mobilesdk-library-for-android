//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/24/2015.
// */
//public final class DeleteOptionValueRequest extends OrganizationRequest {
//
//    public DeleteOptionValueRequest() {
//        this.setRequestName("DeleteOptionValue");
//        this.setValue(0);
//    }
//
//    @Nullable
//    public String getOptionSetName() {
//        if (this.getParameters().containsKey("OptionSetName")) {
//            return this.getParameters().get("OptionSetName").toString();
//        }
//        return null;
//    }
//
//    public void setOptionSetName(String value) {
//        this.set("OptionSetName", value);
//    }
//
//    @Nullable
//    public String getAttributeLogicalName() {
//        if (this.getParameters().containsKey("AttributeLogicalName")) {
//            return this.getParameters().get("AttributeLogicalName").toString();
//        }
//        return null;
//    }
//
//    public void setAttributeLogicalName(String value) {
//        this.set("AttributeLogicalName", value);
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
//
//    public int getValue() {
//        if (this.getParameters().containsKey("Value")) {
//            return (int)this.getParameters().get("Value");
//        }
//        return 0;
//    }
//
//    public void setValue(int value) {
//        this.set("Value", value);
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
