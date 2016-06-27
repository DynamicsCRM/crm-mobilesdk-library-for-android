package com.microsoft.xrm.sdk;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created on 4/1/15.
 */
public final class AliasedValue {


    private String AttributeLogicalName;
    private String EntityLogicalName;
    private Object Value;

    public Object getValue() {
        return Value;
    }

    public String getAttributeLogicalName() {
        return AttributeLogicalName;
    }

    public String getEntityLogicalName() {
        return EntityLogicalName;
    }

    public void setAttributeLogicalName(String attributeLogicalName) {
        this.AttributeLogicalName = attributeLogicalName;
    }

    public void setEntityLogicalName(String entityLogicalName) {
        this.EntityLogicalName = entityLogicalName;
    }

    public void setValue(Object value) {
        Value = value;
    }

    static AliasedValue loadFromXml(XmlPullParser parser) {
        AliasedValue aliasedValue = new AliasedValue();

        try {
            String name = parser.getName();
            parser.nextTag();

            if (parser.getName().equals(name) && parser.getEventType() == XmlPullParser.END_TAG) {
                return aliasedValue;
            }

            do {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    parser.next();
                    continue;
                }

                if (parser.getName().equals("AttributeLogicalName")) {
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        aliasedValue.setAttributeLogicalName(parser.getText());
                    }
                }
                else if(parser.getName().equals("EntityLogicalName")) {
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        aliasedValue.setEntityLogicalName(parser.getText());
                    }
                }
                else if (parser.getName().equals("Value")) {
                    aliasedValue.setValue(Utils.objectFromXml(parser));
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

        return aliasedValue;
    }
}
