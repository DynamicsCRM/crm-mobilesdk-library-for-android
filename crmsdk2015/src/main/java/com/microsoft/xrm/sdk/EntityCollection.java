package com.microsoft.xrm.sdk;

import com.google.gson.Gson;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 3/2/2015.
 */
public class EntityCollection {

    private String EntityName;
    private DataCollection<Entity> entities;
    private boolean moreRecords;
    private String pagingCookie;
    private String minActiveRowVersion;
    private int totalRecordCount;
    private boolean totalRecordCountLimitExceeded;
    private boolean isReadOnly;

    public EntityCollection() {

    }

    public EntityCollection(List<Entity> list) {
        this.entities = new DataCollection<Entity>(list);
    }


    public DataCollection<Entity> getEntities() {
        if (this.entities == null) {
            entities = new DataCollection<Entity>();
        }
        return this.entities;
    }

    public void setEntities(DataCollection<Entity> value) {
        this.entities = value;
    }

    public boolean getMoreRecords() {
        return this.moreRecords;
    }

    public void setMoreRecords(boolean value) throws IllegalAccessException {
        this.checkIsReadOnly();
        this.moreRecords = value;
    }

    public String getPagingCookie() {
        return this.pagingCookie;
    }

    public void setPagingCookie(String value) throws IllegalAccessException {
        this.checkIsReadOnly();
        this.pagingCookie = value;
    }

    public String getMinActiveRowVersion() {
        return this.minActiveRowVersion;
    }

    public void setMinActiveRowVersion(String value) throws IllegalAccessException {
        this.checkIsReadOnly();
        this.minActiveRowVersion = value;
    }

    public int getTotalRecordCount() {
        return this.totalRecordCount;
    }

    public void setTotalRecordCount(int value) throws IllegalAccessException {
        this.checkIsReadOnly();
        this.totalRecordCount = value;
    }

    public boolean getTotalRecordCountLimitExceeded() {
        return this.totalRecordCountLimitExceeded;
    }

    public void setTotalRecordCountLimitExceeded(boolean value) throws IllegalAccessException {
        this.checkIsReadOnly();
        this.totalRecordCountLimitExceeded = value;
    }

    public Entity get(int index) {
        return this.entities.get(index);
    }

    public String getEntityName() {
        return EntityName;
    }

    public void setEntityName(String value) throws IllegalAccessException {
        this.checkIsReadOnly();
        this.EntityName = value;
    }

    boolean getIsReadOnly() {
        return this.isReadOnly;
    }

    void setIsReadOnly(boolean value) {
        this.isReadOnly = value;
    }

    private void checkIsReadOnly() throws IllegalAccessException {
        if (this.isReadOnly) {
            throw new IllegalAccessException("The collection is read-only");
        }
    }

    String toValueXml()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.objectToXml(this.entities.toArray(), "a:Entities", true));
        stringBuilder.append(Utils.objectToXml(EntityName, "a:EntityName", true));
        stringBuilder.append(Utils.objectToXml(this.minActiveRowVersion, "a:MinActiveRowVersion", true));
        stringBuilder.append(Utils.objectToXml(this.moreRecords, "a:MoreRecords", true));
        stringBuilder.append(Utils.objectToXml(this.pagingCookie, "a:PagingCookie", true));
        stringBuilder.append(Utils.objectToXml(this.totalRecordCount, "a:TotalRecordCount", true));
        stringBuilder.append(Utils.objectToXml(this.totalRecordCountLimitExceeded, "a:TotalRecordCountLimitExceeded", true));
        return stringBuilder.toString();
    }

    String toValueJson() {
        Gson gson = new Gson();
        ArrayList<Object> entities = new ArrayList<>();

        for (Entity entity : this.entities) {
            entities.add(Utils.getSchemaAttributes(entity));
        }

        return gson.toJson(entities);
    }

    public static EntityCollection loadFromXml(XmlPullParser parser) {
        EntityCollection entityCollection = new EntityCollection();

        try {
            String name = parser.getName();
            parser.nextTag();
            do {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    parser.next();
                    continue;
                }

                if (parser.getName().equals("Entities")) {
                    parser.nextTag();
                    if (parser.getName().equals("Entity")) {
                        do {
                            entityCollection.getEntities().add(Entity.loadFromXml(parser));
                            parser.nextTag();
                        } while (!parser.getName().equals("Entities"));
                    }
                }

                else if (parser.getName().equals("EntityName")) {
                    parser.next();
                    entityCollection.setEntityName(parser.getText());
                }
                else if (parser.getName().equals("MinActiveRowVersion")) {
                    parser.next();
                    entityCollection.setMinActiveRowVersion(parser.getText());
                }
                else if (parser.getName().equals("PagingCookie")) {
                    parser.next();
                    entityCollection.setMinActiveRowVersion(parser.getText());
                }
                else if (parser.getName().equals("MoreRecords")) {
                    parser.next();
                    entityCollection.setMoreRecords(Boolean.parseBoolean(parser.getText()));
                }
                else if (parser.getName().equals("TotalRecordCount")) {
                    parser.next();
                    entityCollection.setTotalRecordCount(Integer.parseInt(parser.getText()));
                }
                else if (parser.getName().equals("TotalRecordCountLimitExceeded")) {
                    parser.next();
                    entityCollection.setTotalRecordCountLimitExceeded(Boolean.parseBoolean(parser.getText()));
                }
                else {
                    Utils.skip(parser);
                }

                parser.next();
            } while(!parser.getName().equals(name));

            entityCollection.setTotalRecordCount(entityCollection.getEntities().size());
        }
        catch(Exception ex) {
            ex.getCause().printStackTrace();
        }

        return entityCollection;
    }
}
