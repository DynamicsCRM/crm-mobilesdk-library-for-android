package com.microsoft.xrm.sdk;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.microsoft.xrm.sdk.Query.FetchExpression;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


/**
 * Created on 3/30/2015.
 */
public class Utils {

    public static String encodeXML(String data) {
        return data
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&apos;");
    }

    public static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        long depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    static String returnTypeNameForArray(Object item) {
        return item.getClass().getSimpleName().substring(0, item.getClass().getSimpleName().length() - 2);
    }

    private static String getSchemaName(Class<?> entityType, String logicalName) {
        for (Method method : entityType.getMethods()) {
            AttributeLogicalNameAttribute annotation = method
                    .getAnnotation(AttributeLogicalNameAttribute.class);

            if (annotation != null && annotation.value().equals(logicalName)) {
                return method.getAnnotation(AttributeSchemaNameAttribute.class).value();
            }
        }

        return logicalName;
    }

    public static Object parseAttribute(LinkedTreeMap<String, Object> treeMap) {
        Gson gson = new Gson();

        String[] type = ((LinkedTreeMap) treeMap.get("__metadata")).get("type").toString().split("\\.");
        switch (type[type.length - 1]) {
            case "OptionSetValue":
                return gson.fromJson(gson.toJson(treeMap), OptionSetValue.class);
            case "Money":
                return gson.fromJson(gson.toJson(treeMap), Money.class);
            case "EntityReference":
                return gson.fromJson(gson.toJson(treeMap), EntityReference.class);
            case "EntityCollection":
//                return EntityCollection.loadFromJson(treeMap);
            default:
                return null;
        }

    }

    public static Object getAttributeValue(String value) {
        if (value.contains("/Date(")) {
            String[] split = value.split("[()]");

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(split[1]));

            return calendar.getTime();
        }
        if (value.split("-").length == 5) {
            return UUID.fromString(value);
        }

        return value;
    }

    public static HashMap<String, Object> getSchemaAttributes(Entity entity) {
        HashMap<String, Object> SchemaAttributes = new HashMap<>();

        AttributeCollection attributes = entity.getAttributes();
        for (String key : attributes.keySet()) {
            Object value = attributes.get(key);

            switch(value.getClass().getSimpleName()) {
                case "Date":
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    value = format.format((Date)value);
                    break;
                case "EntityCollection":
                    value = ((EntityCollection)value).toValueJson();
                    break;
            }

            SchemaAttributes.put(getSchemaName(entity.getClass(), key), value);
        }

        return SchemaAttributes;
    }

    public static Object objectFromXml(XmlPullParser parser) {
        try {
            Object value = null;
            // Obtain CrmType. I haven't cover all types yet.
            String CrmType = parser.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type").substring(2);
            switch (CrmType) {
                case "long":
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        value = Long.parseLong(parser.getText());
                    }
                    break;
                case "decimal":
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        value = BigDecimal.valueOf(Double.parseDouble(parser.getText()));
                    }
                    break;
                case "string":
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        value = parser.getText();
                    }
                    break;
                case "int":
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        value = Integer.parseInt(parser.getText());
                    }
                    break;
                case "double":
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        value = Double.parseDouble(parser.getText());
                    }
                    break;
                case "dateTime":
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD'T'HH:mm:ss'Z'");
                        value = simpleDateFormat.parse(parser.getText());
                    }
                    break;
                case "guid":
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        value = UUID.fromString(parser.getText());
                    }
                    break;
                case "boolean":
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        value = Boolean.parseBoolean(parser.getText());
                    }
                    break;
                case "EntityReference":
                    value = EntityReference.loadFromXml(parser);
                    break;
                case "AliasedValue":
                    value = AliasedValue.loadFromXml(parser);
                    break;
                case "OptionSetValue":
                    value = OptionSetValue.loadFromXml(parser);
                    break;
                case "Money":
                    value = Money.loadFromXml(parser);
                    break;
                case "EntityCollection":
                    value = EntityCollection.loadFromXml(parser);
                    break;
                default:
                    break;
            }
            return value;
        }
        catch(Exception ex) {
            ex.getCause().printStackTrace();
            return null;
        }
    }


    public static String objectToXml(Object item, String action, Boolean elementOnly) {
        if (item == null) {
            return String.format("<%s i:nil='true' />", action);
        }
        if (item.getClass().isArray() && Array.getLength(item) == 0
                && !action.equals("b:value")) {
            return String.format("<%s />", action);
        }

        String type = "";
        String value = "";

        if (item.getClass().isEnum()) {
            switch (item.getClass().getSimpleName()) {
                case "PrivilegeDepth":
                case "PropagationOwnershipOptions":
                case "RollupType":
                case "SearchDirection":
                case "TargetFieldType":
                case "TimeCode":
                    type = "g:" + item.getClass().getSimpleName();
                    break;
                case "AssociatedMenuBehavior":
                case "AssociatedMenuGroup":
                case "AttributeTypeCode":
                case "CascadeType":
                case "DateTimeFormat":
                case "ImeMode":
                case "OptionSetType":
                case "OwnershipTypes":
                case "PrivilegeType":
                case "StringFormat":
                    type = "h:" + item.getClass().getSimpleName();
                    break;
                case "ConditionOperator":
                case "EntityRole":
                case "JoinOperator":
                case "LogicalOperator":
                case "OrderType":
                case "RelationshipType":
                case "SecurityTypes":
                    type = "a:" + item.getClass().getSimpleName();
                    break;
                case "MetadataConditionOperator":
                    type = "j:" + item.getClass().getSimpleName();
                    break;
            }

            if (value.equals("")) {
                value = item.toString();
            }
        }

        else if (item.getClass().isArray()) {
            String arrayType = "";
            boolean valueOnlyForDataCollection = false;

            if (item.getClass().getClass().isEnum()) {
                type = "a:ArrayOf" + returnTypeNameForArray(item);
            }
            switch (item.getClass().getSimpleName()) {
                case "ActivityParty[]":
                    EntityCollection entityCollection = new EntityCollection();
                    for (Entity record : (Entity[])item) {
                        entityCollection.getEntities().add(record);
                    }
                    return Utils.objectToXml(entityCollection, "b:value", null);
                case "Entity[]":
                case "Money[]":
                case "OptionSetValue[]":
                    type = "a:ArrayOf" + returnTypeNameForArray(item);
                    arrayType = "a:" + returnTypeNameForArray(item);
                    break;
                case "EntityReference[]":
                case "LocalizedLabel[]":
                    arrayType = "a:" + returnTypeNameForArray(item);
                    valueOnlyForDataCollection = true;
                    break;
                case "ObjectiveRelation[]":
                case "RequiredResource[]":
                case "RolePrivilege[]":
                case "TimeCode[]":
                    type = "g:ArrayOf" + returnTypeNameForArray(item);
                    arrayType = "g:" + returnTypeNameForArray(item);
                    break;
                case "ManyToManyRelationshipMetadata[]":
                case "OneToManyRelationshipMetadata[]":
                case "SecurityPrivilegeMetadata[]":
                    type = "h:ArrayOf" + returnTypeNameForArray(item);
                    arrayType = "h:" + returnTypeNameForArray(item);
                    break;
                case "OptionMetadata[]":
                    arrayType = "h:" + returnTypeNameForArray(item);
                    valueOnlyForDataCollection = true;
                    break;
                case "Byte[]":
                    type = "c:base64Binary";
                    value = Base64.encodeToString((byte[])item, 0);
                    break;
                case "Boolean[]":
                    type = "f:ArrayOfboolean";
                    arrayType = "f:boolean";
                    break;
                case "Date[]":
                    type = "f:ArrayOfdateTime";
                    arrayType = "f:dateTime";
                    break;
                case "int[]":
                    type = "f:ArrayOfint";
                    arrayType = "f:int";
                    break;
                case "Integer[]":
                    type = "f:ArrayOflong";
                    arrayType = "f:long";
                    break;
                default:
                    type = "f:ArrayOf" + returnTypeNameForArray(item).toLowerCase();
                    arrayType = "f:" + returnTypeNameForArray(item).toLowerCase();
                    break;
            }

            if (Array.getLength(item) == 0) {
                return String.format("<%s i:type='%s' />", action, type);
            }

            if (!arrayType.equals("")) {
                for (Object obj : (Object[])item) {
                    value += objectToXml(obj, arrayType, true);
                }
                if (valueOnlyForDataCollection) {
                    return value;
                }
            }
        }
        else {
            String objType = item.getClass().getSimpleName();
            if (item.getClass().getSuperclass() == Entity.class) {
                objType = "Entity";
            }
            switch (objType) {
                case "ColumnSet":
                    type = "a:ColumnSet";
                    value = ((ColumnSet)item).toValueXml();
                    break;
                case "Entity":
                    type = "a:Entity";
                    value = ((Entity)item).toValueXml();
                    break;
                case "EntityCollection":
                    type = "a:EntityCollection";
                    value = ((EntityCollection)item).toValueXml();
                    break;
                case "EntityReference":
                    type = "a:EntityReference";
                    value = ((EntityReference)item).toValueXml();
                    break;
                case "EntityReferenceCollection":
                    type = "a:EntityReferenceCollection";
                    if (((EntityReferenceCollection)item).size() == 0) {
                        if (action.equals("b:value")) {
                            return String.format("<%s i:type='%s' />", action, type);
                        }
                        else {
                            return String.format("<%s />", action);
                        }
                    }
                    else {
                        value = ((EntityReferenceCollection) item).toValueXml();
                    }
                    break;
                case "FetchExpression":
                    type = "a:FetchExpression";
                    value = ((FetchExpression)item).toValueXml();
                    break;
                case "Label":
                    type = "a:Label";
                    value = ((Label)item).toValueXml();
                    break;
                case "LocalizedLabel":
                    type = "a:LocalizedLabel";
                    value = ((LocalizedLabel)item).toValueXml();
                    break;
                case "LocalizedLabelCollection":
                    type = "a:LocalizedLabelCollection";
                    if (((LocalizedLabelCollection)item).size() == 0) {
                        if (action.equals("b:value")) {
                            return String.format("<%s i:type='%s' />", action, type);
                        }
                        else {
                            return String.format("<%s />", action);
                        }
                    }
                    else {
                        value = ((LocalizedLabelCollection) item).toValueXml();
                    }
                    break;
                case "Money":
                    type = "a:Money";
                    value = ((Money)item).toValueXml();
                    break;
                case "OptionSetValue":
                    type = "a:OptionSetValue";
                    value = ((OptionSetValue)item).toValueXml();
                    break;
                case "Relationship":
                    type = "a:Relationship";
                    value = ((Relationship)item).toValueXml();
                    break;
                case "Date":
                    type = "c:dateTime";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD'T'HH:mm:ss'Z'");
                    value = simpleDateFormat.format((Date)item);
                    break;
                case "Boolean":
                case "boolean":
                    type = "c:boolean";
                    value = item.toString().toLowerCase();
                    break;
                case "UUID":
                case "uuid":
                    type = "e:guid";
                    break;
                case "int":
                case "Integer":
                    type = "c:int";
                    break;
                case "long":
                    type = "c:long";
                    break;
                case "BigDecimal":
                    type = "c:decimal";
                    break;
                case "String":
                    type = "c:string";
                    value = Utils.encodeXML(item.toString());
                    break;
                default:
                    type = "c:" + item.getClass().getSimpleName().toLowerCase();
                    break;
            }

            if (value.equals("")) {
                value = item.toString();
            }
        }

        if (elementOnly != null && (boolean)elementOnly) {
            return String.format("<%1$s>%2$s</%1$s>", action, value);
        }
        else {
            return String.format("<%1$s i:type='%2$s'>%3$s</%1$s>", action, type, value);
        }
    }

}
