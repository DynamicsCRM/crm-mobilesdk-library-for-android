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
//public final class RetrieveRelationshipRequest extends OrganizationRequest {
//
//    public RetrieveRelationshipRequest() {
//        this.setRequestName("RetrieveRelationship");
//        this.setMetadataId(new UUID(0L, 0L));
//        this.setRetrieveAsIfPublished(false);
//    }
//
//    @Nullable
//    public String getName() {
//        if (this.getParameters().containsKey("Name")) {
//            return this.getParameters().get("Name").toString();
//        }
//        return null;
//    }
//
//    public void setName(String value) {
//        this.set("Name", value);
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
