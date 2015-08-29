package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.EntityCollection;
import com.microsoft.xrm.sdk.OrganizationResponse;
import com.microsoft.xrm.sdk.Utils;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created on 3/26/2015.
 */
public class BackgroundSendEmailResponse extends OrganizationResponse {

    private EntityCollection entityCollection;
    private boolean[] hasAttachments;

    public boolean[] getHasAttachments() {
        return hasAttachments;
    }

    public EntityCollection getEntityCollection() {
        if (entityCollection == null) {
            return new EntityCollection();
        }

        return entityCollection;
    }

    public void setEntityCollection(EntityCollection entityCollection) {
        this.entityCollection = entityCollection;
    }

    public void setHasAttachments(boolean[] hasAttachments) {
        this.hasAttachments = hasAttachments;
    }

    @Override
    public void storeResult(XmlPullParser parser) {
        try {

            String name = parser.getName();
            parser.nextTag();

            do {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    parser.next();
                    continue;
                }

                if (parser.getName().equals("KeyValuePairOfstringanyType")) {
                    parser.nextTag();
                    do {
                        if (parser.getEventType() != XmlPullParser.START_TAG) {
                            parser.next();
                            continue;
                        }

                        if (parser.getName().equals("key")) {
                            parser.next();
                            if (parser.getText().equals("EntityCollection")) {
                                parser.nextTag();
                                parser.nextTag();
                                this.entityCollection = (EntityCollection)Utils.objectFromXml(parser);
                            }
                            if (parser.getText().equals("HasAttachments")) {
                                parser.nextTag();
                                parser.nextTag();

                            }
                        }

                        parser.next();
                    } while (!parser.getName().equals("KeyValuePairOfstringanyType"));

                }
                else {
                    Utils.skip(parser);
                }

                parser.next();
            } while (parser.getEventType() != XmlPullParser.END_TAG || !parser.getName().equals(name));

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
