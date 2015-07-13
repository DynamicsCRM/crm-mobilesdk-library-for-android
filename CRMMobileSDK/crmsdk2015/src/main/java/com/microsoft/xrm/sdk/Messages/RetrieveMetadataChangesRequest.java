//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/24/2015.
// */
//public final class RetrieveMetadataChangesRequest extends OrganizationRequest {
//
//    public RetrieveMetadataChangesRequest() {
//        this.setRequestName("RetrieveMetadataChanges");
//    }
//
//    @Nullable
//    public EntityQueryExpression getQuery() {
//        if (this.getParameters().containsKey("Query")) {
//            return (EntityQueryExpression) this.getParameters().get("Query");
//        }
//        return null;
//    }
//
//    public void setQuery(EntityQueryExpression value) {
//        this.set("Query", value);
//    }
//
//    public DeletedMetadataFilters DeletedMetadataFilters() {
//        if (this.getParameters().containsKey("DeletedMetadataFilters")) {
//            return (DeletedMetadataFilters) this.getParameters().get("DeletedMetadataFilters");
//        }
//        return (DeletedMetadataFilters)0;
//    }
//
//    public void setDeletedMetadataFilters(DeletedMetadataFilters value) {
//        this.set("DeletedMetadataFilters", value);
//    }
//
//    @Nullable
//    public String getClientVersionStamp() {
//        if (this.getParameters().containsKey("ClientVersionStamp")) {
//            return this.getParameters().get("ClientVersionStamp").toString();
//        }
//        return null;
//    }
//
//    public void setClientVersionStamp(String value) {
//        this.set("ClientVersionStamp", value);
//    }
//}
