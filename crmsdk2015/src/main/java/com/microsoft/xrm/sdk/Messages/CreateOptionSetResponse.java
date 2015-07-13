package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.OrganizationResponse;
import com.microsoft.xrm.sdk.Utils;

import org.xmlpull.v1.XmlPullParser;

import java.util.UUID;

/**
 * Created on 3/31/2015.
 */
public final class CreateOptionSetResponse extends OrganizationResponse {

    private UUID optionSetId;

    public UUID getOptionSetId() {
        return optionSetId;
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
                            if (parser.getText().equals("OptionSetId")) {
                                parser.nextTag();
                                parser.nextTag();
                                this.optionSetId = (UUID) Utils.objectFromXml(parser);
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
