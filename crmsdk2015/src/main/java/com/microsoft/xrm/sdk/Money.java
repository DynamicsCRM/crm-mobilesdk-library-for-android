package com.microsoft.xrm.sdk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;

import java.math.BigDecimal;

public class Money {

    /**
     * Initializes a new instance of the Money class.
     */
    public Money() {

    }

    /**
     * Initializes a new instance of the Money class setting the value.
     * @param value The value of the attribute.
     */
    public Money(BigDecimal value) {
        this.Value = value;
    }

    /**
     * Gets or sets the value of the attribute.
     */
    @Expose
    @SerializedName("Value")
    public BigDecimal Value;

    /**
     * Determines whether two instances are equal.
     * @param obj Money to compare with the current Money.
     * @return true if the specified Money is equal to the Money object; otherwise, false.
     */
    public boolean Equals(Object obj) {
        return this.equals(obj);
    }

    String toValueXml() {
        return Utils.objectToXml(Value, "a:Value", true);
    }

    static Money loadFromXml(XmlPullParser parser) {
        Money money = new Money();

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
                        money.Value = new BigDecimal(Double.parseDouble(parser.getText()));
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

        return money;
    }
}
