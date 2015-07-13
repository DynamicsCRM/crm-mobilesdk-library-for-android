package com.microsoft.xrm.sdk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;

public class OptionSetValue {

    /**
     * Initializes a new instance of the OptionSetValue class
     */
    public OptionSetValue() {

    }

    /**
     * Initializes a new instance of the OptionSetValue class
     * @param value The option set value.
     */
    public OptionSetValue(int value) {
        this.Value = value;
    }

    /**
     * Gets or sets the current value.
     */
    @Expose
    @SerializedName("Value")
    private int Value;

    public int getValue() {
        return this.Value;
    }

    public void setValue(int value) {
        this.Value = value;
    }

    String toValueXml() {
        return Utils.objectToXml(getValue(), "a:Value", true);
    }

    static OptionSetValue loadFromXml(XmlPullParser parser) {
        OptionSetValue optionSetValue = null;

        try {
            String name = parser.getName();
            parser.next();

            do {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    parser.next();
                    continue;
                }

                if (parser.getName().equals("Value")) {
                    parser.next();
                    if (parser.getEventType() == XmlPullParser.TEXT) {
                        optionSetValue = new OptionSetValue(Integer.parseInt(parser.getText()));
                    }
                }
                else {
                    Utils.skip(parser);
                }

                parser.next();
            } while (!parser.getName().equals(name));

        }
        catch(Exception ex) {
            ex.getCause().printStackTrace();
        }

        return optionSetValue;
    }
}
