//package com.microsoft.xrm.sdk.Messages;
//
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/24/2015.
// */
//public final class RetrieveAllEntitiesRequest extends OrganizationRequest {
//
//    public RetrieveAllEntitiesRequest() {
//        this.setRequestName("RetrieveAllEntities");
//        this.setEntityFilters((EntityFilters)0);
//        this.setRetrieveAsIfPublished(false);
//    }
//
//    public EntityFilters getEntityFilters() {
//        if (this.getParameters().containsKey("EntityFilters")) {
//            return (EntityFilters)this.getParameters().get("EntityFilters");
//        }
//        return (EntityFilters) 0;
//    }
//
//    public void setEntityFilters(EntityFilters value) {
//        this.set("EntityFilters", value);
//    }
//
//    public boolean getRetrieveAsIfPublished() {
//        if(this.getParameters().containsKey("RetrieveAsIfPublished")) {
//            return (boolean)this.getParameters().get("RetrieveAsIfPublished");
//        }
//        return false;
//    }
//
//    public void setRetrieveAsIfPublished(boolean value) {
//        this.set("RetrieveAsIfPublished", value);
//    }
//}
