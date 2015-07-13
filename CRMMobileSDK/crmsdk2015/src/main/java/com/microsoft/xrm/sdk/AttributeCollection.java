package com.microsoft.xrm.sdk;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created on 3/6/2015.
 */
public final class AttributeCollection extends DataMapCollection<String, Object> {

    String ToXml() {
        StringBuilder stringBuilder = new StringBuilder();

        if (this.size() == 0) {
            return stringBuilder.append("<a:Attributes/>").toString();
        }
        stringBuilder.append("<a:Attributes>");
        for (String key : this.keySet()) {
            stringBuilder.append("<a:KeyValuePairOfstringanyType>");
            stringBuilder.append("<b:key>" + Utils.encodeXML(key) + "</b:key>");
            stringBuilder.append(Utils.objectToXml(this.get(key), "b:value", null));
            stringBuilder.append("</a:KeyValuePairOfstringanyType>");
        }
        stringBuilder.append("</a:Attributes>");
        return stringBuilder.toString();
    }

    static AttributeCollection LoadFromXml(XmlPullParser parser)
    {
        AttributeCollection attributeCollection = new AttributeCollection();

        try {
            String name = parser.getName();
            parser.nextTag();

            do {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    parser.next();
                    continue;
                }

                if (parser.getName().equals("KeyValuePairOfstringanyType")) {
                    String key = "";
                    Object value = null;

                    parser.nextTag();
                    do {
                        if (parser.getEventType() != XmlPullParser.START_TAG) {
                            parser.next();
                            continue;
                        }

                        if (parser.getName().equals("key")) {
                            parser.next();
                            if (parser.getEventType() == XmlPullParser.TEXT) {
                                key = parser.getText();
                            }
                        }
                        else if(parser.getName().equals("value")) {
                            value = Utils.objectFromXml(parser);
                        }

                        parser.next();
                    } while(!parser.getName().equals("KeyValuePairOfstringanyType"));

                    if (!key.equals("") && value != null) {
                        attributeCollection.put(key, value);
                    }
                }

                parser.next();
            } while(!parser.getName().equals(name));

        }
        catch(Exception ex) {
            ex.getCause().printStackTrace();
        }

        return attributeCollection;
    }
}
