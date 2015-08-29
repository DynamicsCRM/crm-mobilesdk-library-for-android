package com.microsoft.xrm.sdk;

import android.os.Bundle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;

import java.util.UUID;

public class EntityReference {


    private static final String ID = "_id";
    private static final String LOGICAL_NAME = "logical_name";
    private static final String NAME = "name";

    @SerializedName("Id")
    @Expose
    private UUID Id;

    @SerializedName("LogicalName")
    @Expose
    private String LogicalName;

    @SerializedName("Name")
    @Expose
    private String Name;

    public static EntityReference build() {
        return new EntityReference();
    }

    public String getLogicalName() {
        return LogicalName;
    }

    public String getName() {
        return Name;
    }

    public UUID getId() {
        return Id;
    }

    public EntityReference setId(UUID id) {
        Id = id;
        return this;
    }

    public EntityReference setLogicalName(String logicalName) {
        LogicalName = logicalName;
        return this;
    }

    public EntityReference setName(String name) {
        Name = name;
        return this;
    }

    public EntityReference() {

    }

    public static EntityReference fromBundle(Bundle bundle) {
        return EntityReference.build()
                .setId(UUID.fromString(bundle.getString(ID)))
                .setLogicalName(bundle.getString(LOGICAL_NAME))
                .setName(bundle.getString(NAME));
    }

    public Bundle toBundle() {
        Bundle newBundle = new Bundle();
        newBundle.putString(ID, getId().toString());
        newBundle.putString(LOGICAL_NAME, getLogicalName());
        newBundle.putString(NAME, getName());

        return newBundle;
    }

    /**
     * Initializes a new instance of the EntityReference class setting
     * the logical name and entity ID.
     * @param logicalName The logical name of the entity.
     * @param id The ID of the record.
     */
    public EntityReference(String logicalName, UUID id) {
        this.Id = id;
        this.LogicalName = logicalName;
    }

    String toValueXml() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.objectToXml(Id, "a:Id", true));
        stringBuilder.append(Utils.objectToXml(LogicalName, "a:LogicalName", true));
        stringBuilder.append(Utils.objectToXml(Name, "a:Name", true));
        return stringBuilder.toString();
    }

    static EntityReference loadFromXml(XmlPullParser  parser) {
        EntityReference entityReference = new EntityReference();

        try {
            String name = parser.getName();
            parser.nextTag();

            do {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    parser.next();
                    continue;
                }

                if (parser.getName().equals("Id")) {
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        entityReference.setId(UUID.fromString(parser.getText()));
                    }
                }
                else if (parser.getName().equals("LogicalName")) {
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        entityReference.setLogicalName(parser.getText());
                    }
                }
                else if (parser.getName().equals("Name")) {
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        entityReference.setName(parser.getText());
                    }
                }
                else {
                    Utils.skip(parser);
                }

                parser.next();
            } while(!parser.getName().equals(name));

        }
        catch(Exception ex) {
            ex.getCause().printStackTrace();
        }

        return entityReference;
    }
}
