//package com.microsoft.xrm.sdk.Messages;
//
//import android.support.annotation.Nullable;
//
//import com.microsoft.xrm.sdk.OrganizationRequest;
//
///**
// * Created on 3/6/2015.
// */
//public final class CreateEntityRequest extends OrganizationRequest {
//
//    public CreateEntityRequest() {
//        this.setRequestName("CreateEntity");
//        this.setEntity(null);
//        this.setHasActivities(false);
//        this.setHasNotes(false);
//        this.setPrimaryAttribute(null);
//    }
//
//    @Nullable
//    public EntityMetadata getEntity() {
//        if (this.getParameters().containsKey("Entity")) {
//            return (EntityMetadata) this.getParameters().get("Entity");
//        }
//        return null;
//    }
//
//    public void setEntity(EntityMetadata value) {
//        this.set("Entity", value);
//    }
//
//    @Nullable
//    public Boolean getHasActivities() {
//        if (this.getParameters().containsKey("HasActivities")) {
//            return (boolean) this.getParameters().get("HasActivities");
//        }
//        return null;
//    }
//
//    public void setHasActivities(Boolean value) {
//        this.set("HasActivities", value);
//    }
//
//    @Nullable
//    public Boolean getHasNotes() {
//        if (this.getParameters().containsKey("HasNotes")) {
//            return (boolean) this.getParameters().get("HasNotes");
//        }
//        return null;
//    }
//
//    public void setHasNotes(Boolean value) {
//        this.set("HasNotes", value);
//    }
//
//    @Nullable
//    public StringAttributeMetadata getPrimaryAttribute() {
//        if (this.getParameters().containsKey("PrimaryAttribute")) {
//            return (StringAttributeMetadata) this.getParameters().get("PrimaryAttribute");
//        }
//        return null;
//    }
//
//    public void setPrimaryAttribute(StringAttributeMetadata value) {
//        this.set("PrimaryAttribute", value);
//    }
//
//
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
