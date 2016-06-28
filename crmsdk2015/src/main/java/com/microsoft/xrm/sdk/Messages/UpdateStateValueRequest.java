//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.Label;
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
//public final class UpdateStateValueRequest extends OrganizationRequest {
//
//    public UpdateStateValueRequest() {
//        this.setRequestName("UpdateStateValue");
//        this.setValue(0);
//        this.setMergeLabels(false);
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
//            return (int) this.getParameters().get("Value");
//        }
//        return 0;
//    }
//
//    public void setValue(int value) {
//        this.set("Value", value);
//    }
//
//    @Nullable
//    public Label getLabel() {
//        if (this.getParameters().containsKey("Label")) {
//            return (Label) this.getParameters().get("Label");
//        }
//
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
//
//        return null;
//    }
//
//    public void setDescription(Label value) {
//        this.set("Description", value);
//    }
//
//    public boolean getMergeLabels() {
//        if (this.getParameters().containsKey("MergeLabels")) {
//            return (boolean) this.getParameters().get("MergeLabels");
//        }
//        return false;
//    }
//
//    public void setMergeLabels(boolean value) {
//        this.set("MergeLabels", value);
//    }
//
//    @Nullable
//    public Integer getDefaultStatusCode() {
//        if (this.getParameters().containsKey("DefaultStatusCode")) {
//            return (Integer) this.getParameters().get("DefaultStatusCode");
//        }
//
//        return null;
//    }
//
//    public void setDefaultStatusCode(Integer value) {
//        this.set("DefaultStatusCode", value);
//    }
//}
