//package com.microsoft.xrm.sdk;
//
//import java.util.UUID;
//
///**
// * Created on 3/6/2015.
// */
//public final class AttributePrivilege {
//    private UUID attributeId;
//    private int canCreate;
//    private int canRead;
//    private int canUpdate;
//
//    public AttributePrivilege() {
//
//    }
//
//    /**
//     * Initializes a new instance of the AttributePrivilege class setting the attribute Id, and whether it is valid to create, read and update the attribute value.
//     * @param attributeId The attribute ID.
//     * @param canCreate Whether the attribute value can be specified on create.
//     * @param canRead Whether the attribute value can be read.
//     * @param canUpdate Whether the attribute value can be updated.
//     */
//    public AttributePrivilege(UUID attributeId, int canCreate, int canRead, int canUpdate) {
//        this.attributeId = attributeId;
//        this.canCreate = canCreate;
//        this.canRead = canRead;
//        this.canUpdate = canUpdate;
//    }
//
//    public UUID getAttributeId() {
//        return this.attributeId;
//    }
//
//    void setAttributeId(UUID value) {
//        this.attributeId = value;
//    }
//
//    public int getCanCreate() {
//        return this.canCreate;
//    }
//
//    void setCanCreate(int value) {
//        this.canCreate = value;
//    }
//
//    public int getCanRead() {
//        return this.canRead;
//    }
//
//    void setCanRead(int value) {
//        this.canRead = value;
//    }
//
//    public int getCanUpdate() {
//        return this.canUpdate;
//    }
//
//    void setCanUpdate(int value) {
//        this.canUpdate = value;
//    }
//
////    static AttributePrivilege LoadFromXml(XElement item)
////    {
////        AttributePrivilege attributePrivilege = new AttributePrivilege()
////        {
////            AttributeId = Util.LoadFromXml<Guid>(item.Element(Util.ns.a + "AttributeId")),
////            CanCreate = Util.LoadFromXml<int>(item.Element(Util.ns.a + "CanCreate")),
////            CanRead = Util.LoadFromXml<int>(item.Element(Util.ns.a + "CanRead")),
////            CanUpdate = Util.LoadFromXml<int>(item.Element(Util.ns.a + "CanUpdate"))
////        };
////        return attributePrivilege;
////    }
//}
