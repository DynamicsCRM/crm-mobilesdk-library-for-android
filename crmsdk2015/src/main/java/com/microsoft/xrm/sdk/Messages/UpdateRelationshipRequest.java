//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.Metadata.RelationshipMetadataBase;
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/24/2015.
// */
//public final class UpdateRelationshipRequest extends OrganizationRequest {
//
//    public UpdateRelationshipRequest() {
//        this.setRequestName("UpdateRelationship");
//        this.setRelationship(null);
//        this.setMergeLabels(false);
//    }
//
//    @Nullable
//    public RelationshipMetadataBase getRelationship() {
//        if (this.getParameters().containsKey("Relationship")) {
//            return (RelationshipMetadataBase) this.getParameters().get("Relationship");
//        }
//        return null;
//    }
//
//    public void setRelationship(RelationshipMetadataBase value) {
//        this.set("Relationship", value);
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
