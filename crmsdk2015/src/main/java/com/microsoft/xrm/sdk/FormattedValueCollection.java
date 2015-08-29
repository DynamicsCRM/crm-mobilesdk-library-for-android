package com.microsoft.xrm.sdk;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created on 3/6/2015.
 */
public final class FormattedValueCollection extends DataMapCollection<String, String> {

    String toXml() {
        StringBuilder stringBuilder = new StringBuilder();

        if (this.size() == 0) {
            return stringBuilder.append("<a:FormattedValues/>").toString();
        }

        stringBuilder.append("<a:FormattedValues>");
        for (String key : this.keySet()) {
            stringBuilder.append("<a:KeyValuePairOfstringstring>");
            stringBuilder.append("<b:key>" + Utils.encodeXML(key) + "</b:key><b:value>" + Utils.encodeXML(this.get(key)) + "</b:value>");
            stringBuilder.append("</a:KeyValuePairOfstringstring>");
        }
        stringBuilder.append("</a:FormattedValues>");
        return stringBuilder.toString();
    }

    static FormattedValueCollection loadFromXml(XmlPullParser parser) {
        FormattedValueCollection formattedValueCollection = new FormattedValueCollection();

        try {
            String name = parser.getName();
            parser.nextTag();

            if (parser.getName().equals(name) && parser.getEventType() == XmlPullParser.END_TAG) {
                return formattedValueCollection;
            }

            do {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    parser.next();
                    continue;
                }

                if (parser.getName().equals("KeyValuePairOfstringstring")) {
                    String key = "";
                    String value = "";

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
                            parser.next();
                            if (parser.getEventType() == XmlPullParser.TEXT) {
                                value = parser.getText();
                            }
                        }

                        parser.next();
                    } while(!parser.getName().equals("KeyValuePairOfstringstring"));

                    if (!key.equals("") && value != null) {
                        formattedValueCollection.put(key, value);
                    }
                }

                parser.next();
            } while(!parser.getName().equals(name));

//            if (vtdNav.toElement(VTDNav.FIRST_CHILD)) {
//                do {
//                    String key = "";
//                    String value = "";
//
//                    VTDNav dataNav = vtdNav.cloneNav();
//                    if (dataNav.toElement(VTDNav.FIRST_CHILD)) {
//                        do {
//                            if (dataNav.getText() != -1) {
//                                if (dataNav.toNormalizedString(dataNav.getCurrentIndex()).equals("b:key")) {
//                                    key = dataNav.toNormalizedString(dataNav.getText());
//                                }
//                                if (dataNav.toNormalizedString(dataNav.getCurrentIndex()).equals("b:value")) {
//                                    value = dataNav.toNormalizedString(dataNav.getText());
//                                }
//                            }
//                        } while (dataNav.toElement(VTDNav.NEXT_SIBLING));
//                    }
//
//                    formattedValueCollection.put(key, value);
//                } while (vtdNav.toElement(VTDNav.NEXT_SIBLING));
//            }
        }
        catch(Exception ex) {
            ex.getCause().printStackTrace();
        }

        return formattedValueCollection;
    }

}
