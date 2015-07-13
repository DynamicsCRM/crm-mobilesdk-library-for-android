package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationResponse;
import com.microsoft.xrm.sdk.Utils;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created on 3/27/2015.
 */
public final class SendEmailResponse extends OrganizationResponse {

    private String subject;

    public String getSubject() {
        return subject;
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
                            if (parser.getText().equals("Subject")) {
                                parser.nextTag();
                                parser.nextTag();
                                this.subject = parser.getText();
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
