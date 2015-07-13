package com.microsoft.xrm.sdk;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created on 3/5/2015.
 */
public class OrganizationResponse {

    public String ResponseName;
    public DataMapCollection<String, Object> Results;

    public OrganizationResponse() {

    }

    public String getResponseName() {
        return this.ResponseName;
    }

    public void setResponseName(String responseName) {
        this.ResponseName = responseName;
    }

    public DataMapCollection<String, Object> getResults() {
        if (this.Results == null) {
            this.Results = new DataMapCollection<>();
        }

        return this.Results;
    }

    public void setResults(DataMapCollection<String, Object> value) {
        this.Results = value;
    }

    public void storeResult(XmlPullParser parser) {

    }
}
