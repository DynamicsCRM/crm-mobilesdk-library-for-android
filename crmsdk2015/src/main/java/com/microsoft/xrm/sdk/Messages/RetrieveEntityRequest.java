//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
//import java.util.UUID;
//
///**
// * Created on 3/24/2015.
// */
//public final class RetrieveEntityRequest extends OrganizationRequest {
//
//    public RetrieveEntityRequest() {
//        this.setRequestName("RetrieveEntity");
//        this.setEntityFilters((EntityFilters) 0);
//        this.setMetadataId(new UUID(0L, 0L));
//        this.setRetrieveAsIfPublished(false);
//    }
//
//    public EntityFilters getEntityFilters() {
//        if (this.getParameters().containsKey("EntityFilters")) {
//            return (EntityFilters) this.getParameters().get("EntityFilters");
//        }
//        return (EntityFilters)0;
//    }
//
//    public void setEntityFilters(EntityFilters value) {
//        this.set("EntityFilters", value);
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
//
//    public UUID getMetadataId() {
//        if (this.getParameters().containsKey("MetadataId")) {
//            return (UUID) this.getParameters().get("MetadataId");
//        }
//        return new UUID(0L, 0L);
//    }
//
//    public void setMetadataId(UUID value) {
//        this.set("MetadataId", value);
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
