//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.Label;
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/24/2015.
// */
//public final class InsertStatusValueRequest extends OrganizationRequest {
//
//    public InsertStatusValueRequest() {
//        this.setRequestName("InsertStatusValue");
//        this.setLabel(null);
//        this.setStateCode(0);
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
//    @Nullable
//    public Integer getValue() {
//        if (this.getParameters().containsKey("Value")) {
//            return (Integer) this.getParameters().get("Value");
//        }
//        return null;
//    }
//
//    public void setValue(Integer value) {
//        this.set("Value", value);
//    }
//
//    @Nullable
//    public Label getLabel() {
//        if (this.getParameters().containsKey("Label")) {
//            return (Label) this.getParameters().get("Label");
//        }
//        return null;
//    }
//
//    public void setLabel(Label value) {
//        this.set("Label", value);
//    }
//
//    @Nullable
//    public Label getDescription() {
//        if (this.getParameters().containsKey("Description")) {
//            return (Label) this.getParameters().get("Description");
//        }
//        return null;
//    }
//
//    public void setDescription(Label value) {
//        this.set("Description", value);
//    }
//
//    public int getStateCode() {
//        if (this.getParameters().containsKey("StateCode")) {
//            return (int)this.getParameters().get("StateCode");
//        }
//        return 0;
//    }
//
//    public void setStateCode(int value) {
//        this.set("StateCode", value);
//    }
//
//    @Nullable
//    public String getSolutionUniqueName() {
//        if(this.getParameters().containsKey("SolutionUniqueName")) {
//            return this.getParameters().get("SolutionUniqueName").toString();
//        }
//        return null;
//    }
//
//    public void setSolutionUniqueName(String value) {
//        this.set("SolutionUniqueName", value);
//    }
//
//
//}
