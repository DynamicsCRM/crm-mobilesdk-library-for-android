package com.microsoft.xrm.sdk.Messages;

import com.microsoft.xrm.sdk.Utils;

import org.xmlpull.v1.XmlPullParser;

import java.util.UUID;

/**
 * Created on 3/31/2015.
 */
public class ValidationResult {
    private UUID activityId;
    private TraceInfo traceInfo;
    private boolean ValidationSuccess;

    public TraceInfo getTraceInfo() {
        return traceInfo;
    }

    public UUID getActivityId() {
        return activityId;
    }

    public boolean getValidateSuccess() {
        return ValidationSuccess;
    }

    public void setActivityId(UUID activityId) {
        this.activityId = activityId;
    }

    public void setTraceInfo(TraceInfo traceInfo) {
        this.traceInfo = traceInfo;
    }

    public void setValidationSuccess(boolean validationSuccess) {
        ValidationSuccess = validationSuccess;
    }

    static ValidationResult LoadFromXml(XmlPullParser parser) {
        ValidationResult validationResult = new ValidationResult();

        try {
            String name = parser.getName();
            parser.nextTag();

            do {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    parser.next();
                    continue;
                }

                if (parser.getName().equals("ActivityId")) {
                    validationResult.setActivityId((UUID)Utils.objectFromXml(parser));
                }
                if (parser.getName().equals("TraceInfo")) {
//                        validationResult.setTraceInfo(TraceInfo.LoadFromXml(vtdNav.cloneNav()));
                }
                if (parser.getName().equals("ValidationSuccess")) {
                    validationResult.setValidationSuccess((boolean)Utils.objectFromXml(parser));
                }

                parser.next();
            } while(!parser.getName().equals(name));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        return validationResult;
    }
}
