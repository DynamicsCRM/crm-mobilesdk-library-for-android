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
//public final class RetrieveManagedPropertyRequest extends OrganizationRequest {
//
//    public RetrieveManagedPropertyRequest() {
//        this.setRequestName("RetrieveManagedProperty");
//        this.setMetadataId(new UUID(0L, 0L));
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
//}
