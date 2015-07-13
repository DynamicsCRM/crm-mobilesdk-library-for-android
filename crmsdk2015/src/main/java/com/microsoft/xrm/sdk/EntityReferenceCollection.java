package com.microsoft.xrm.sdk;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

/**
 * Created on 3/5/2015.
 */
public class EntityReferenceCollection extends DataCollection<EntityReference> {

    public EntityReferenceCollection() {

    }

    public EntityReferenceCollection(List<EntityReference> list) {
        super(list);
    }

    String toValueXml()
    {
        return Utils.objectToXml(this.toArray(), "a:EntityReference", true);
    }

    public static EntityReferenceCollection loadFromXml(XmlPullParser parser) {
        EntityReferenceCollection entityReferences = new EntityReferenceCollection();

        try {
            String name = parser.getName();
            parser.nextTag();

            do {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    parser.next();
                    continue;
                }

                if (parser.getName().equals("EntityReference")) {
                    entityReferences.add(EntityReference.loadFromXml(parser));
                }
                else {
                    Utils.skip(parser);
                }

                parser.next();
            } while(!parser.getName().equals(name));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        return entityReferences;
    }
}
