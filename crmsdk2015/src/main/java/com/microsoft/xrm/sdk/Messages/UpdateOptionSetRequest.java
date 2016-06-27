//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.Metadata.OptionSetMetadataBase;
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/24/2015.
// */
//public final class UpdateOptionSetRequest extends OrganizationRequest {
//
//    public UpdateOptionSetRequest() {
//        this.setRequestName("UpdateOptionSet");
//        this.setOptionSet(null);
//        this.setMergeLabels(false);
//    }
//
//    @Nullable
//    public OptionSetMetadataBase getOptionSet() {
//        if (this.getParameters().containsKey("OptionSet")) {
//            return (OptionSetMetadataBase) this.getParameters().get("OptionSet");
//        }
//        return null;
//    }
//
//    public void setOptionSet(OptionSetMetadataBase value) {
//        this.set("OptionSet", value);
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
