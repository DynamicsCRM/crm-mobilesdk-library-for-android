package com.microsoft.xrm.sdk;

import com.google.gson.internal.LinkedTreeMap;

import org.xmlpull.v1.XmlPullParser;

import java.io.InvalidClassException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.UUID;

/**
 * Created on 3/2/2015.
 */
public class Entity {

    private UUID Id;
    private String LogicalName;
    private EntityState EntityState;
    boolean isReadOnly;
    private AttributeCollection Attributes;
    private FormattedValueCollection FormattedValues;
    private RelatedEntityCollection RelatedEntities;

    /**
     * Initializes a new instance of the Entity class.
     */
    public Entity() {
        this.Id = new UUID(0L, 0L);
        Attributes = new AttributeCollection();
        FormattedValues = new FormattedValueCollection();
        RelatedEntities = new RelatedEntityCollection();
    }

    /**
     * Initializes a new instance of the Entity class setting the entity name.
     * @param entityName The name of the entity.
     */
    public Entity(String entityName) {
        this();
        LogicalName = entityName;
    }

    /**
     * Gets the ID of the record represented by this entity instance.
     */
    public UUID getId() {
        return Id;
    }

    /**
     * Sets the ID of the record represented by this entity instance.
     */
    public void setId(UUID value) {
        Id = value;
    }

    public EntityState getEntityState() {
        return this.EntityState;
    }

    public void setAttribute(String attributeName, Object value) {
        this.Attributes.put(attributeName, value);
    }

    public void setFormattedValue(String formatedName, String value) {
        this.FormattedValues.put(formatedName, value);
    }

    public Entity setEntityState(EntityState entityState) {
        this.EntityState = entityState;
        return this;
    }

    public String getLogicalName() {
        return LogicalName;
    }

    public Entity setLogicalName(String logicalName) {
        this.LogicalName = logicalName;
        return this;
    }

    public Object get(String attributeName) {
        return this.Attributes.get(attributeName);
    }

    public AttributeCollection getAttributes() {
        if (this.Attributes == null) {
            this.Attributes = new AttributeCollection();
        }

        return Attributes;
    }

    public Entity setAttributes(AttributeCollection value) {
        this.Attributes = value;
        return this;
    }

    public FormattedValueCollection getFormattedValues() {
        if (this.FormattedValues == null) {
            this.FormattedValues = new FormattedValueCollection();
        }

        return this.FormattedValues;
    }

    public void setFormattedValues(FormattedValueCollection value) {
        this.FormattedValues = value;
    }

    public RelatedEntityCollection getRelatedEntities() {
        if (this.RelatedEntities == null) {
            this.RelatedEntities = new RelatedEntityCollection();
            this.RelatedEntities.setIsReadOnly(this.isReadOnly);
        }

        return this.RelatedEntities;
    }

    boolean getIsReadOnly() {
        return this.isReadOnly;
    }

    void setIsReadOnly(boolean value) {
        this.isReadOnly = value;
        this.RelatedEntities.setIsReadOnly(value);
    }

    /**
     * Gets an entity reference for this entity instance.
     * @return The entity reference for the entity.
     */
    public EntityReference toEntityReference() {
        return new EntityReference(this.LogicalName, this.Id);
    }

    /**
     * Checks to see if there is a value present for the specified attribute.
     * @param attributeName The logical name of the attribute.
     * @return true if the Entity contains an attribute with the
     * specified name; otherwise, false.
     */
    public boolean contains(String attributeName) {
        return Attributes.containsKey(attributeName);
    }

    /**
     * Sets the value of an attribute.
     * @param attributeLogicalName The logical name of the attribute.
     * @param value The value to set.
     */
    protected void setAttributeValue(String attributeLogicalName, Object value) {
        if (attributeLogicalName.equals("")) {
            throw new NullPointerException("attributeLogicalName");
        }
        this.Attributes.put(attributeLogicalName, value);
    }

    /**
     * Gets the value of the attribute.
     * @param attributeLogicalName The logical name of the attribute.
     * @param <T> type that will be returned.
     * @return The value of the attribute.
     */
    protected <T> T getAttributeValue(String attributeLogicalName) {
        if (attributeLogicalName.equals("")) {
            throw new NullPointerException("attributeLogicalName");
        }

        Object attribute = Attributes.get(attributeLogicalName);
        if (attribute != null) {
            return (T)Attributes.get(attributeLogicalName);
        }
        else {
            return null;
        }
    }

    public <T> T toEntity(Class<T> type) throws Exception {
        if (this.LogicalName.equals("")) {
            throw new Exception("LogicalName must be set before calling ToEntity");
        }

        Constructor<T> constructor = type.getConstructor();
        Object newEntity = constructor.newInstance();

        Field field = type.getField("EntityLogicalName");
        if (!field.get(newEntity).toString().equals(this.LogicalName)) {
            throw new InvalidClassException("This entity isn't an instance of %s", type.getSimpleName());
        }
        if (type.getSuperclass() != Entity.class) {
            throw new InvalidClassException("%s does not extend Entity", type.getSimpleName());
        }

        ((Entity) newEntity)
                .setIdInternal(this.Id)
                .setLogicalNameInternal(this.LogicalName)
                .setEntityStateInternal(this.EntityState)
                .setAttributes(this.Attributes);

        return (T)newEntity;
    }

    Entity setIdInternal(UUID id) {
        this.Id = id;
        return this;
    }

    Entity setLogicalNameInternal(String logicalName) {
        this.LogicalName = logicalName;
        return this;
    }

    Entity setEntityStateInternal(EntityState entityState) {
        this.EntityState = entityState;
        return this;
    }

    String toValueXml() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.encodeXML(this.Attributes.ToXml()));
        stringBuilder.append("<a:EntityState i:nil='true' />");
        stringBuilder.append(Utils.encodeXML(this.FormattedValues.toXml()));
        stringBuilder.append("<a:Id>");
        stringBuilder.append((this.Id == null || this.Id == new UUID(0L, 0L)) ?
                "00000000-0000-0000-0000-000000000000" : Id.toString());
        stringBuilder.append("</a:Id>");
        stringBuilder.append("<a:LogicalName>" + this.LogicalName + "</a:LogicalName>");
        stringBuilder.append(Utils.encodeXML(this.RelatedEntities.toXml()));
        return stringBuilder.toString();
    }

    public static Entity loadFromXml(XmlPullParser parser) {
        Entity entity = new Entity();

        try {
            String name = parser.getName();
            parser.nextTag();

            if (parser.getName().equals(name) && parser.getEventType() == XmlPullParser.END_TAG) {
                return null;
            }

            do {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    parser.next();
                    continue;
                }

                if (parser.getName().equals("LogicalName")) {
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        entity.setLogicalName(parser.getText());
                    }
                }
                else if (parser.getName().equals("Id")) {
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        entity.setId(UUID.fromString(parser.getText()));
                    }
                }
                else if (parser.getName().equals("Attributes")) {
                    entity.setAttributes(AttributeCollection.LoadFromXml(parser));
                }
                else if (parser.getName().equals("FormattedValues")) {
                    entity.setFormattedValues(FormattedValueCollection.loadFromXml(parser));
                }
                else {
                    Utils.skip(parser);
                }

                parser.next();
            } while (!parser.getName().equals(name));

        }
        catch (Exception ex) {
            ex.getCause().printStackTrace();
        }

        return entity;
    }

    public static Entity loadFromJson(LinkedTreeMap<String, Object> treeMap) {
        Entity entity = new Entity();

        for (String key : treeMap.keySet()) {
            if (!key.equals("__metadata")) {
                Object value = treeMap.get(key);

                if (value instanceof LinkedTreeMap) {
                    if (((LinkedTreeMap<String, Object>)value).containsKey("__metadata")) {
                        entity.getAttributes().put(key.toLowerCase(), Utils.parseAttribute((LinkedTreeMap) value));
                    }
                }
                if (value instanceof String) {
                    entity.getAttributes().put(key.toLowerCase(), Utils.getAttributeValue(value.toString()));
                }
            }
            else {
                String[] type = ((LinkedTreeMap)treeMap.get(key)).get("type").toString().split("\\.");
                entity.setLogicalName(type[type.length - 1].toLowerCase());
            }
        }

        UUID primaryId = new UUID(0L, 0L);
        if (entity.contains(entity.getLogicalName() + "id")) {
            primaryId = UUID.fromString(entity.get(entity.getLogicalName() + "id").toString());
        }
        else if (entity.contains("activityid")) {
            primaryId = UUID.fromString(entity.get("activityid").toString());
        }

        if (primaryId != new UUID(0L, 0L)) {
            entity.setId(UUID.fromString(primaryId.toString()));
        }

        return entity;
    }
}
