//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
//import java.util.UUID;
//
//public final class RetrieveAttributeRequest extends OrganizationRequest {
//
//    public RetrieveAttributeRequest() {
//        this.setRequestName("RetrieveAttribute");
//        this.setMetadataId(new UUID(0L, 0L));
//        this.setRetrieveAsIfPublished(false);
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
//    public int getColumnNumber() {
//        if (this.getParameters().containsKey("ColumnNumber")) {
//            return (int) this.getParameters().get("ColumnNumber");
//        }
//        return 0;
//    }
//
//    public void setColumnNumber(int value) {
//        this.set("ColumnNumber", value);
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
