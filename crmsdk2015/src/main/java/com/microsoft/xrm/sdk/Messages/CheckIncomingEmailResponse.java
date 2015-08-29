package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationResponse;
import com.microsoft.xrm.sdk.Utils;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created on 3/26/2015.
 */
public final class CheckIncomingEmailResponse extends OrganizationResponse {

    private boolean shouldDeliver;

    public boolean getShouldDeliver() {
        return this.shouldDeliver;
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
                            if (parser.getText().equals("ShouldDeliver")) {
                                parser.nextTag();
                                parser.nextTag();
                                this.shouldDeliver = (boolean) Utils.objectFromXml(parser);
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
